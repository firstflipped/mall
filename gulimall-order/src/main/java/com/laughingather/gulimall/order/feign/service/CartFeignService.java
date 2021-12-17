package com.laughingather.gulimall.order.feign.service;

import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.order.feign.entity.OrderItemTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 购物车服务远程调用类
 *
 * @author：laughingather
 * @create：2021-10-18 2021/10/18
 */
@FeignClient("gulimall-cart")
@RequestMapping("/gulimall-cart/openapi/cart")
public interface CartFeignService {

    /**
     * 获取当前用户购物车项
     *
     * @return
     */
    @GetMapping("/current-user-cart-items")
    MyResult<List<OrderItemTO>> getCurrentUserCartItems();

}

