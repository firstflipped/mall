package com.laughingather.gulimall.ware.feign.service;

import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.ware.feign.entity.OrderTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 订单服务调用类
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@FeignClient("gulimall-order")
public interface OrderFeignService {

    /**
     * 调用订单服务获取订单详情
     *
     * @param orderSn
     * @return
     */
    @GetMapping("/gulimall-order/openapi/order/{osn}/info")
    MyResult<OrderTO> getOrderByOrderSn(@PathVariable("osn") String orderSn);

}
