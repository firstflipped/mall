package com.laughingather.gulimall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laughingather.gulimall.order.entity.OrderEntity;
import com.laughingather.gulimall.order.entity.dto.OrderSubmitDTO;
import com.laughingather.gulimall.order.entity.vo.OrderConfirmVO;
import com.laughingather.gulimall.order.entity.vo.OrderSubmitVO;

import java.util.concurrent.ExecutionException;

/**
 * 订单
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-12 11:17:55
 */
public interface OrderService extends IService<OrderEntity> {

    /**
     * 获取订单确认页所需信息
     *
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    OrderConfirmVO confirmOrder() throws ExecutionException, InterruptedException;

    /**
     * 提交订单
     *
     * @param orderSubmitDTO
     * @return
     */
    OrderSubmitVO submitOrder(OrderSubmitDTO orderSubmitDTO);

    /**
     * 根据订单号获取订单详情
     *
     * @param orderSn
     * @return
     */
    OrderEntity getOrderByOrderSn(String orderSn);
}

