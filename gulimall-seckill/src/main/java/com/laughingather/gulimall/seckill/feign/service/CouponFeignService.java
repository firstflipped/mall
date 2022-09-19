package com.laughingather.gulimall.seckill.feign.service;

import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.seckill.feign.entity.SecKillSessionDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * 优惠服务第三方调用
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@FeignClient("gulimall-coupon")
public interface CouponFeignService {

    @GetMapping("/gulimall-coupon/openapi/coupon/last-3days-session")
    MyResult<List<SecKillSessionDTO>> getLast3DaysSession();


}

