package com.laughingather.gulimall.seckill.feign.service;

import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.seckill.feign.entity.SeckillSessionTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 优惠服务第三方调用
 *
 * @author：laughingather
 * @create：2021-11-12 2021/11/12
 */
@FeignClient("gulimall-coupon")
@RequestMapping("/gulimall-coupon/openapi/coupon")
public interface CouponFeignService {

    @GetMapping("/last-3days-session")
    MyResult<List<SeckillSessionTO>> getLast3DaysSession();


}

