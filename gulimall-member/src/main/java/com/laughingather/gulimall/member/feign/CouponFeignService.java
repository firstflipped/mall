package com.laughingather.gulimall.member.feign;

import com.laughingather.gulimall.common.api.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author WangJie
 */
@FeignClient("gulimall-coupon")
public interface CouponFeignService {

    /**
     * 查询优惠券列表
     * @return
     */
    @GetMapping("/coupon/coupon/list")
    Result listCoupons();

}
