package com.flipped.mall.order.controller;

import com.flipped.mall.common.entity.api.MyResult;
import com.flipped.mall.order.entity.OrderItemEntity;
import com.flipped.mall.order.service.OrderItemService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


/**
 * 订单项管理模块
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@RestController
@RequestMapping("order/order-item")
public class OrderItemController {

    @Resource
    private OrderItemService orderItemService;

    @GetMapping("/{osn}/items")
    public MyResult<List<OrderItemEntity>> listOrderItemsByOrderSn(@PathVariable("osn") String orderSn) {
        List<OrderItemEntity> orderItems = orderItemService.listOrderItemsByOrderSn(orderSn);
        return MyResult.success(orderItems);
    }

}
