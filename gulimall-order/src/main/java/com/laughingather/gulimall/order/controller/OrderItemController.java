package com.laughingather.gulimall.order.controller;

import com.laughingather.gulimall.common.entity.api.MyResult;
import com.laughingather.gulimall.order.entity.OrderItemEntity;
import com.laughingather.gulimall.order.service.OrderItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


/**
 * 订单项路由
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@RestController
@RequestMapping("order/order-item")
@Tag(name = "订单项模块")
public class OrderItemController {

    @Resource
    private OrderItemService orderItemService;

    @GetMapping("/{osn}/items")
    @Operation(summary = "根据订单号查询订单项列表")
    public MyResult<List<OrderItemEntity>> listOrderItemsByOrderSn(@PathVariable("osn") String orderSn) {
        List<OrderItemEntity> orderItems = orderItemService.listOrderItemsByOrderSn(orderSn);
        return MyResult.success(orderItems);
    }

}
