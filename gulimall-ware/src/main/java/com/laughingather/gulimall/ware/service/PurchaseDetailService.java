package com.laughingather.gulimall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.ware.entity.PurchaseDetailEntity;
import com.laughingather.gulimall.ware.entity.query.PurchaseDetailQuery;

import java.util.List;

/**
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-12 11:57:23
 */
public interface PurchaseDetailService extends IService<PurchaseDetailEntity> {

    /**
     * 分页查询采购单详情列表
     *
     * @param purchaseDetailQuery
     * @return
     */
    MyPage<PurchaseDetailEntity> listPurchaseDetailsWithPage(PurchaseDetailQuery purchaseDetailQuery);

    /**
     * 根据采购单id获取其下的所有采购项
     *
     * @param purchaseId
     * @return
     */
    List<PurchaseDetailEntity> listPurchaseDetailsByPurchaseId(Long purchaseId);
}

