package com.laughingather.gulimall.order.controller;

import com.laughingather.gulimall.order.service.OrderItemService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * 订单项信息
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-12 11:17:55
 */
@RestController
@RequestMapping("order/orderitem")
public class OrderItemController {
    @Resource
    private OrderItemService orderItemService;

}
