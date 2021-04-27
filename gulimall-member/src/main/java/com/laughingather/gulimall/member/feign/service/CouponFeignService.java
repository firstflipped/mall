package com.laughingather.gulimall.member.feign.service;

import com.laughingather.gulimall.member.feign.entity.CouponEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author WangJie
 */
@FeignClient("gulimall-coupon")
public interface CouponFeignService {

    /**
     * 查询优惠券列表
     *
     * @return
     */
    @GetMapping("/openapi/coupon/coupon/list")
    List<CouponEntity> listCoupons();

}
