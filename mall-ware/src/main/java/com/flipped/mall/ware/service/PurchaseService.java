package com.flipped.mall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.flipped.mall.common.entity.api.MyPage;
import com.flipped.mall.ware.entity.PurchaseEntity;
import com.flipped.mall.ware.entity.param.DonePurchaseParam;
import com.flipped.mall.ware.entity.param.MergePurchaseParam;
import com.flipped.mall.ware.entity.query.PurchaseQuery;

import java.util.List;

/**
 * 采购信息
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
public interface PurchaseService extends IService<PurchaseEntity> {

    /**
     * 分页查询采购信息列表
     *
     * @param purchaseQuery
     * @return
     */
    MyPage<PurchaseEntity> listPurchasesWithPage(PurchaseQuery purchaseQuery);

    /**
     * 查询新建、已分配的采购单列表
     *
     * @return
     */
    List<PurchaseEntity> listUnReceivePurchaseDetail();

    /**
     * 合并采购单
     *
     * @param mergePurchaseParam
     */
    void mergePurchase(MergePurchaseParam mergePurchaseParam);

    /**
     * 保存采购单信息
     *
     * @param purchase
     */
    void savePurchase(PurchaseEntity purchase);

    /**
     * 更新采购单信息
     *
     * @param purchase
     */
    void updatePurchaseById(PurchaseEntity purchase);


    /**
     * 完成采购单信息
     *
     * @param ids
     */
    void receivedPurchase(List<Long> ids);

    /**
     * xxx
     *
     * @param donePurchaseDTO
     */
    void donePurchase(DonePurchaseParam donePurchaseDTO);
}

