package com.laughingather.gulimall.ware.feign.service;

import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.ware.feign.entity.OrderTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 订单服务调用类
 *
 * @author：laughingather
 * @create：2021-10-26 2021/10/26
 */
@FeignClient("gulimall-order")
@RequestMapping("/gulimall-order/openapi/order")
public interface OrderFeignService {

    /**
     * 调用订单服务获取订单详情
     *
     * @param orderSn
     * @return
     */
    @GetMapping("/{osn}/info")
    MyResult<OrderTO> getOrderByOrderSn(@PathVariable("osn") String orderSn);

}
