package com.laughingather.gulimall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.order.entity.OrderReturnReasonEntity;
import com.laughingather.gulimall.order.entity.query.OrderReturnReasonQuery;

/**
 * 退货原因逻辑接口
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-12 11:17:55
 */
public interface OrderReturnReasonService extends IService<OrderReturnReasonEntity> {

    /**
     * 分页查询退货原因列表
     *
     * @param orderReturnReasonQuery
     * @return
     */
    MyPage<OrderReturnReasonEntity> listOrderReturnReasonsWithPage(OrderReturnReasonQuery orderReturnReasonQuery);

    /**
     * 保存退货原因
     *
     * @param orderReturnReason
     */
    void saveOrderReturnReason(OrderReturnReasonEntity orderReturnReason);

    /**
     * 更新退货原因
     *
     * @param orderReturnReason
     */
    void updateOrderReturnReason(OrderReturnReasonEntity orderReturnReason);
}

