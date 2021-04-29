package com.laughingather.gulimall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.ware.entity.PurchaseEntity;
import com.laughingather.gulimall.ware.entity.dto.DonePurchaseDTO;
import com.laughingather.gulimall.ware.entity.dto.MergePurchaseDTO;
import com.laughingather.gulimall.ware.entity.query.PurchaseQuery;

import java.util.List;

/**
 * 采购信息
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-12 11:57:24
 */
public interface PurchaseService extends IService<PurchaseEntity> {
    MyPage<PurchaseEntity> pagePurchaseByParams(PurchaseQuery purchaseQuery);

    List<PurchaseEntity> listUnreceivePurchaseDetail();

    void mergePurchase(MergePurchaseDTO mergePurchaseDTO);

    void savePurchase(PurchaseEntity purchase);

    void updatePurchaseById(PurchaseEntity purchase);

    void receivedPurchase(List<Long> ids);

    void donePurchase(DonePurchaseDTO donePurchaseDTO);
}

