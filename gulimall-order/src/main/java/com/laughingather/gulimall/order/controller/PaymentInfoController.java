package com.laughingather.gulimall.order.controller;

import com.laughingather.gulimall.order.service.PaymentInfoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * 支付信息路由
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-12 11:17:55
 */
@RestController
@RequestMapping("order/payment-info")
public class PaymentInfoController {
    @Resource
    private PaymentInfoService paymentInfoService;

}
