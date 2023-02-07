package com.laughingather.gulimall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.flipped.mall.common.entity.api.MyPage;
import com.laughingather.gulimall.order.entity.OrderEntity;
import com.laughingather.gulimall.order.entity.dto.PayDTO;
import com.laughingather.gulimall.order.entity.param.OrderSubmitParam;
import com.laughingather.gulimall.order.entity.query.OrderQuery;
import com.laughingather.gulimall.order.entity.vo.OrderConfirmVO;
import com.laughingather.gulimall.order.entity.vo.OrderSubmitVO;

import java.util.concurrent.ExecutionException;

/**
 * 订单
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
public interface OrderService extends IService<OrderEntity> {

    /**
     * 分页查询订单列表
     *
     * @param orderQuery
     * @return
     */
    MyPage<OrderEntity> listOrdersWithPage(OrderQuery orderQuery);

    /**
     * 根据订单号获取订单详情
     *
     * @param orderSn
     * @return
     */
    OrderEntity getOrderByOrderSn(String orderSn);


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
     * @param orderSubmitParam
     * @return
     */
    OrderSubmitVO submitOrder(OrderSubmitParam orderSubmitParam);


    /**
     * 关闭订单
     *
     * @param orderId
     */
    void closeOrder(Long orderId);

    /**
     * 获取订单支付所需信息
     *
     * @param orderSn 订单号
     * @return
     */
    PayDTO getPayOrderInfo(String orderSn);

}

