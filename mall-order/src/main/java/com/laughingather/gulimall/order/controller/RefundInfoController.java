package com.laughingather.gulimall.order.controller;

import com.laughingather.gulimall.order.service.RefundInfoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * 退款信息路由
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@RestController
@RequestMapping("order/refund-info")
public class RefundInfoController {

    @Resource
    private RefundInfoService refundInfoService;

}
