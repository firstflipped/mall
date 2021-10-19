package com.laughingather.gulimall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laughingather.gulimall.order.entity.OrderEntity;
import com.laughingather.gulimall.order.entity.vo.OrderConfirmVO;

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
}

