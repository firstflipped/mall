package com.laughingather.gulimall.coupon.controller;

import com.laughingather.gulimall.common.api.Result;
import com.laughingather.gulimall.coupon.entity.CouponEntity;
import com.laughingather.gulimall.coupon.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 优惠券信息
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-12 11:49:53
 */
@RestController
@RequestMapping("/coupon/coupon")
public class CouponController {
    @Autowired
    private CouponService couponService;

    @GetMapping("/list")
    public Result listCoupons() {
        List<CouponEntity> couponList = couponService.listCoupons();
        return Result.success(couponList);
    }

}
