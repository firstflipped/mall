package com.laughingather.gulimall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.flipped.mall.common.entity.api.MyPage;
import com.laughingather.gulimall.order.entity.OrderReturnApplyEntity;
import com.laughingather.gulimall.order.entity.query.OrderReturnApplyQuery;

/**
 * 订单退货申请逻辑接口
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
public interface OrderReturnApplyService extends IService<OrderReturnApplyEntity> {

    /**
     * 分页查询订单退货申请列表
     *
     * @param orderReturnApplyQuery
     * @return
     */
    MyPage<OrderReturnApplyEntity> listOrderReturnAppliesWithPage(OrderReturnApplyQuery orderReturnApplyQuery);
}

