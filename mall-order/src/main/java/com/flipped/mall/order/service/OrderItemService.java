package com.flipped.mall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.flipped.mall.order.entity.OrderItemEntity;

import java.util.List;

/**
 * 订单项逻辑接口
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
public interface OrderItemService extends IService<OrderItemEntity> {

    /**
     * 根据订单号获取订单项列表信息
     *
     * @param orderSn
     * @return
     */
    List<OrderItemEntity> listOrderItemsByOrderSn(String orderSn);
}

