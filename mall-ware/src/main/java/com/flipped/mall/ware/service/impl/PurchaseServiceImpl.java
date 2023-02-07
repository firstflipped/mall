package com.flipped.mall.ware.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.flipped.mall.common.constant.WareConstants;
import com.flipped.mall.common.entity.api.MyPage;
import com.flipped.mall.ware.dao.PurchaseDao;
import com.flipped.mall.ware.entity.PurchaseDetailEntity;
import com.flipped.mall.ware.entity.PurchaseEntity;
import com.flipped.mall.ware.entity.param.DonePurchaseItemParam;
import com.flipped.mall.ware.entity.param.DonePurchaseParam;
import com.flipped.mall.ware.entity.param.MergePurchaseParam;
import com.flipped.mall.ware.entity.query.PurchaseQuery;
import com.flipped.mall.ware.service.PurchaseDetailService;
import com.flipped.mall.ware.service.PurchaseService;
import com.flipped.mall.ware.service.WareSkuService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Service("purchaseService")
public class PurchaseServiceImpl extends ServiceImpl<PurchaseDao, PurchaseEntity> implements PurchaseService {

    @Resource
    private PurchaseDao purchaseDao;
    @Resource
    private PurchaseDetailService purchaseDetailService;
    @Resource
    private WareSkuService wareSkuService;

    @Override
    public MyPage<PurchaseEntity> listPurchasesWithPage(PurchaseQuery purchaseQuery) {
        IPage<PurchaseEntity> page = new Page<>(purchaseQuery.getPn(), purchaseQuery.getPs());
        QueryWrapper<PurchaseEntity> queryWrapper = null;
        if (StringUtils.isNotBlank(purchaseQuery.getKey())) {
            queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().like(PurchaseEntity::getAssigneeName, purchaseQuery.getKey())
                    .or().like(PurchaseEntity::getPhone, purchaseQuery.getKey());
        }

        IPage<PurchaseEntity> purchasePage = purchaseDao.selectPage(page, queryWrapper);
        return MyPage.restPage(purchasePage);
    }

    @Override
    public List<PurchaseEntity> listUnReceivePurchaseDetail() {
        QueryWrapper<PurchaseEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(PurchaseEntity::getStatus, 0).or().eq(PurchaseEntity::getStatus, 1);

        return purchaseDao.selectList(queryWrapper);
    }

    @Override
    public void savePurchase(PurchaseEntity purchase) {
        purchase.setCreateTime(LocalDateTime.now());
        purchaseDao.insert(purchase);
    }

    @Override
    public void updatePurchaseById(PurchaseEntity purchase) {
        purchase.setUpdateTime(LocalDateTime.now());
        purchaseDao.updateById(purchase);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void mergePurchase(MergePurchaseParam mergePurchaseParam) {
        Long purchaseId = mergePurchaseParam.getPurchaseId();
        // 没有选中采购单的情况下会新建一个采购单
        purchaseId = createNewPurchase(purchaseId);

        // 确定采购单的状态为新建或者已分配的情况下才允许合并
        PurchaseEntity purchaseById = purchaseDao.selectById(purchaseId);
        if (WareConstants.PurchaseEnum.CREATED.getCode().equals(purchaseById.getStatus()) ||
                WareConstants.PurchaseEnum.ASSIGNED.getCode().equals(purchaseById.getStatus())) {

            // 修改采购清单里的内容信息
            updatePurchaseDetails(mergePurchaseParam.getItems(), purchaseId);

            // 修改采购单更新时间
            PurchaseEntity purchase = PurchaseEntity.builder().id(purchaseId).updateTime(LocalDateTime.now()).build();
            purchaseDao.updateById(purchase);
        }
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void receivedPurchase(List<Long> ids) {
        // 确认当前采购单的状态是否为新增或已分配
        List<PurchaseEntity> unreceivedPurchase = getUnreceivedPurchase(ids);

        // 改变采购单的状态
        updateBatchPurchase(unreceivedPurchase);

        // 改变采购项的状态
        updateBatchPurchaseDetail(unreceivedPurchase);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void donePurchase(DonePurchaseParam donePurchaseParam) {
        // 获取采购单id
        Long purchaseId = donePurchaseParam.getPurchaseId();

        // 改变采购项状态
        Boolean flag = updateDonePurchaseDetails(donePurchaseParam);

        // 改变采购单状态
        updateDonePurchase(purchaseId, flag);

        // 将成功采购的进行入库
    }


    /**
     * 创建一个新的采购单，并返回其id
     *
     * @param purchaseId
     * @return
     */
    private Long createNewPurchase(Long purchaseId) {
        if (purchaseId == null) {
            PurchaseEntity purchase = PurchaseEntity.builder().status(WareConstants.PurchaseEnum.CREATED.getCode())
                    .createTime(LocalDateTime.now()).build();
            purchaseDao.insert(purchase);
            purchaseId = purchase.getId();
        }
        return purchaseId;
    }

    /**
     * 修改采购清单里的内容信息（所属采购单id，状态）
     *
     * @param items
     * @param purchaseId
     */
    private void updatePurchaseDetails(List<Long> items, Long purchaseId) {
        if (CollectionUtils.isNotEmpty(items)) {
            List<PurchaseDetailEntity> purchaseDetails = items.stream().map(item ->
                    PurchaseDetailEntity.builder().id(item).purchaseId(purchaseId)
                            .status(WareConstants.PurchaseDetailEnum.ASSIGNED.getCode()).build()
            ).collect(Collectors.toList());
            purchaseDetailService.updateBatchById(purchaseDetails);
        }
    }

    /**
     * 获取当前采购单的状态为新增或已分配的
     *
     * @param ids
     * @return
     */
    private List<PurchaseEntity> getUnreceivedPurchase(List<Long> ids) {

        List<PurchaseEntity> unreceivedPurchase = ids.stream().map(id ->
                purchaseDao.selectById(id)
        ).filter(item ->
                item.getStatus().equals(WareConstants.PurchaseEnum.CREATED.getCode()) ||
                        item.getStatus().equals(WareConstants.PurchaseEnum.ASSIGNED.getCode())
        ).collect(Collectors.toList());

        return unreceivedPurchase;
    }

    /**
     * 改变采购单的状态
     *
     * @param unreceivedPurchase
     */
    private void updateBatchPurchase(List<PurchaseEntity> unreceivedPurchase) {
        if (CollectionUtils.isNotEmpty(unreceivedPurchase)) {
            List<PurchaseEntity> purchases = unreceivedPurchase.stream().map(purchase -> {
                purchase.setStatus(WareConstants.PurchaseEnum.RECEIVE.getCode());
                purchase.setUpdateTime(LocalDateTime.now());
                return purchase;
            }).collect(Collectors.toList());

            this.updateBatchById(purchases);
        }
    }

    /**
     * 改变采购项的状态
     *
     * @param unreceivedPurchase
     */
    private void updateBatchPurchaseDetail(List<PurchaseEntity> unreceivedPurchase) {
        if (CollectionUtils.isNotEmpty(unreceivedPurchase)) {
            unreceivedPurchase.stream().forEach(purchase -> {
                // 获取该采购单下的所有采购项
                List<PurchaseDetailEntity> purchaseDetails = purchaseDetailService.listPurchaseDetailsByPurchaseId(purchase.getId());

                List<PurchaseDetailEntity> collect = purchaseDetails.stream().map(detail ->
                        PurchaseDetailEntity.builder().id(detail.getId()).status(WareConstants.PurchaseDetailEnum.BUYING.getCode()).build()
                ).collect(Collectors.toList());
                purchaseDetailService.updateBatchById(collect);
            });
        }
    }

    /**
     * 更新完成的采购单
     *
     * @param purchaseId
     * @param flag
     */
    private void updateDonePurchase(Long purchaseId, Boolean flag) {
        PurchaseEntity purchase = new PurchaseEntity();
        purchase.setId(purchaseId);
        purchase.setStatus(flag ? WareConstants.PurchaseEnum.FINISH.getCode() : WareConstants.PurchaseEnum.ERROR.getCode());
        purchase.setUpdateTime(LocalDateTime.now());
        purchaseDao.updateById(purchase);
    }

    /**
     * 更新完成的采购项
     *
     * @param donePurchaseParam
     * @return
     */
    private Boolean updateDonePurchaseDetails(DonePurchaseParam donePurchaseParam) {
        boolean flag = true;
        List<DonePurchaseItemParam> items = donePurchaseParam.getItems();
        List<PurchaseDetailEntity> purchaseDetails = new ArrayList<>();
        for (DonePurchaseItemParam item : items) {
            PurchaseDetailEntity purchaseDetail = new PurchaseDetailEntity();
            if (WareConstants.PurchaseDetailEnum.ERROR.getCode().equals(item.getStatus())) {
                flag = false;
                purchaseDetail.setStatus(item.getStatus());
            } else {
                purchaseDetail.setStatus(WareConstants.PurchaseDetailEnum.FINISH.getCode());
                // 将采购成功的进行入库（更新库存信息）
                updateWareSku(item);
            }

            purchaseDetail.setId(item.getItemId());
            purchaseDetails.add(purchaseDetail);
        }
        purchaseDetailService.updateBatchById(purchaseDetails);
        return flag;
    }

    /**
     * 更新库存信息
     *
     * @param donePurchaseItemParam
     */
    private void updateWareSku(DonePurchaseItemParam donePurchaseItemParam) {
        PurchaseDetailEntity byId = purchaseDetailService.getById(donePurchaseItemParam.getItemId());
        wareSkuService.addStock(byId.getSkuId(), byId.getWareId(), byId.getSkuNum());
    }

}