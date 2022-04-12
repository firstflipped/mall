package com.laughingather.gulimall.order.controller;

import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.order.entity.OrderEntity;
import com.laughingather.gulimall.order.entity.query.OrderQuery;
import com.laughingather.gulimall.order.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * 订单路由
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@RestController
@RequestMapping("order/order")
@Api(tags = "订单模块")
public class OrderController {

    @Resource
    private OrderService orderService;

    @GetMapping("/page")
    @ApiOperation(value = "分页查询订单列表")
    public MyResult<MyPage<OrderEntity>> listOrdersWithPage(@ModelAttribute OrderQuery orderQuery) {
        MyPage<OrderEntity> orderPage = orderService.listOrdersWithPage(orderQuery);
        return MyResult.success(orderPage);
    }

}
