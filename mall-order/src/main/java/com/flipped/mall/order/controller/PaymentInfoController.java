package com.flipped.mall.order.controller;

import com.flipped.mall.order.service.PaymentInfoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * 支付信息路由
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@RestController
@RequestMapping("order/payment-info")
public class PaymentInfoController {

    @Resource
    private PaymentInfoService paymentInfoService;

}
