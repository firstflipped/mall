package com.laughingather.gulimall.order.openapi;

import com.laughingather.gulimall.common.entity.api.MyResult;
import com.laughingather.gulimall.order.entity.OrderEntity;
import com.laughingather.gulimall.order.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 订单服务对外提供接口
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@RestController
@RequestMapping("/openapi/order")
public class OrderOpenApi {

    @Resource
    private OrderService orderService;

    /**
     * 根据订单id获取订单详情
     *
     * @param orderSn
     * @return
     */
    @GetMapping("/{osn}/info")
    public MyResult<OrderEntity> getOrderByOrderSn(@PathVariable("osn") String orderSn) {
        OrderEntity order = orderService.getOrderByOrderSn(orderSn);
        return MyResult.success(order);
    }

}

