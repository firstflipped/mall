package com.laughingather.gulimall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laughingather.gulimall.order.entity.OrderItemEntity;

import java.util.List;

/**
 * 订单项逻辑接口
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-12 11:17:55
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

