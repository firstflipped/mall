package com.laughingather.gulimall.ware.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.common.constant.WareConstants;
import com.laughingather.gulimall.ware.dao.PurchaseDao;
import com.laughingather.gulimall.ware.entity.PurchaseDetailEntity;
import com.laughingather.gulimall.ware.entity.PurchaseEntity;
import com.laughingather.gulimall.ware.entity.dto.DonePurchaseDTO;
import com.laughingather.gulimall.ware.entity.dto.DonePurchaseItemDTO;
import com.laughingather.gulimall.ware.entity.dto.MergePurchaseDTO;
import com.laughingather.gulimall.ware.entity.query.PurchaseQuery;
import com.laughingather.gulimall.ware.service.PurchaseDetailService;
import com.laughingather.gulimall.ware.service.PurchaseService;
import com.laughingather.gulimall.ware.service.WareSkuService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service("purchaseService")
public class PurchaseServiceImpl extends ServiceImpl<PurchaseDao, PurchaseEntity> implements PurchaseService {

    @Resource
    private PurchaseDao purchaseDao;
    @Resource
    private PurchaseDetailService purchaseDetailService;
    @Resource
    private WareSkuService wareSkuService;

    @Override
    public MyPage<PurchaseEntity> pagePurchaseByParams(PurchaseQuery purchaseQuery) {
        IPage<PurchaseEntity> page = new Page<>(purchaseQuery.getPageNumber(), purchaseQuery.getPageSize());
        QueryWrapper<PurchaseEntity> queryWrapper = null;
        if (StringUtils.isNotBlank(purchaseQuery.getKey())) {
            queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().like(PurchaseEntity::getAssigneeName, purchaseQuery.getKey())
                    .or().like(PurchaseEntity::getPhone, purchaseQuery.getKey());
        }
        IPage<PurchaseEntity> purchaseIPage = purchaseDao.selectPage(page, queryWrapper);
        return MyPage.restPage(purchaseIPage);
    }

    @Override
    public List<PurchaseEntity> listUnreceivePurchaseDetail() {
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
    @Transactional
    public void mergePurchase(MergePurchaseDTO mergePurchaseDTO) {
        Long purchaseId = mergePurchaseDTO.getPurchaseId();
        // 没有选中采购单的情况下会新建一个采购单
        purchaseId = createNewPurchase(purchaseId);

        // 确定采购单的状态为新建或者已分配的情况下才允许合并
        PurchaseEntity purchaseById = purchaseDao.selectById(purchaseId);
        if (WareConstants.PurchaseEnum.CREATED.getCode().equals(purchaseById.getStatus()) ||
                WareConstants.PurchaseEnum.ASSIGNED.getCode().equals(purchaseById.getStatus())) {

            // 修改采购清单里的内容信息
            updatePurchaseDetails(mergePurchaseDTO.getItems(), purchaseId);

            // 修改采购单更新时间
            PurchaseEntity purchase = PurchaseEntity.builder().id(purchaseId).updateTime(LocalDateTime.now()).build();
            purchaseDao.updateById(purchase);
        }
    }


    @Override
    @Transactional
    public void receivedPurchase(List<Long> ids) {
        // 确认当前采购单的状态是否为新增或已分配
        List<PurchaseEntity> unreceivedPurchase = getUnreceivedPurchase(ids);

        // 改变采购单的状态
        updateBatchPurchase(unreceivedPurchase);

        // 改变采购项的状态
        updateBatchPurchaseDetail(unreceivedPurchase);
    }


    @Override
    @Transactional
    public void donePurchase(DonePurchaseDTO donePurchaseDTO) {
        // 获取采购单id
        Long purchaseId = donePurchaseDTO.getPurchaseId();

        // 改变采购项状态
        Boolean flag = updateDonePurchaseDetails(donePurchaseDTO);

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
        purchase.setStatus(flag ? WareConstants.PurchaseEnum.FINISH.getCode() : WareConstants.PurchaseEnum.HASERROR.getCode());
        purchase.setUpdateTime(LocalDateTime.now());
        purchaseDao.updateById(purchase);
    }

    /**
     * 更新完成的采购项
     *
     * @param donePurchaseDTO
     * @return
     */
    private Boolean updateDonePurchaseDetails(DonePurchaseDTO donePurchaseDTO) {
        Boolean flag = true;
        List<DonePurchaseItemDTO> items = donePurchaseDTO.getItems();
        List<PurchaseDetailEntity> purchaseDetails = new ArrayList();
        for (DonePurchaseItemDTO item : items) {
            PurchaseDetailEntity purchaseDetail = new PurchaseDetailEntity();
            if (item.getStatus() == WareConstants.PurchaseDetailEnum.HASERROR.getCode()) {
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
     * @param item
     */
    private void updateWareSku(DonePurchaseItemDTO item) {
        PurchaseDetailEntity byId = purchaseDetailService.getById(item.getItemId());
        wareSkuService.addStock(byId.getSkuId(), byId.getWareId(), byId.getSkuNum());
    }

}