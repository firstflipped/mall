package com.laughingather.gulimall.order.controller;

import com.laughingather.gulimall.order.service.OrderReturnApplyService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * 订单退货申请
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-12 11:17:55
 */
@RestController
@RequestMapping("order/orderreturnapply")
public class OrderReturnApplyController {
    @Resource
    private OrderReturnApplyService orderReturnApplyService;

}
