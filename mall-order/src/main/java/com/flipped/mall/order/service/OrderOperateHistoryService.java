package com.flipped.mall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.flipped.mall.order.entity.OrderOperateHistoryEntity;

import java.util.List;

/**
 * 订单操作历史记录逻辑接口
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
public interface OrderOperateHistoryService extends IService<OrderOperateHistoryEntity> {


    /**
     * 根据订单id查询订单操作历史记录列表
     *
     * @param orderId
     * @return
     */
    List<OrderOperateHistoryEntity> listHistoriesByOrderId(Long orderId);
}

