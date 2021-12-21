package com.laughingather.gulimall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.order.entity.OrderReturnApplyEntity;
import com.laughingather.gulimall.order.entity.query.OrderReturnApplyQuery;

/**
 * 订单退货申请逻辑接口
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-12 11:17:55
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

