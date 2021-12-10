package com.laughingather.gulimall.order.controller;

import com.laughingather.gulimall.order.service.OrderOperateHistoryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * 订单操作历史记录
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-12 11:17:55
 */
@RestController
@RequestMapping("order/order-operate-history")
public class OrderOperateHistoryController {
    @Resource
    private OrderOperateHistoryService orderOperateHistoryService;

}
