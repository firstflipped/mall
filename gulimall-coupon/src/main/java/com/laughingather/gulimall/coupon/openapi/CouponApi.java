package com.laughingather.gulimall.coupon.openapi;

import com.laughingather.gulimall.coupon.entity.CouponEntity;
import com.laughingather.gulimall.coupon.service.CouponService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/openapi/coupon/coupon")
public class CouponApi {

    @Autowired
    private CouponService couponService;

    @GetMapping("/list")
    public List<CouponEntity> listCoupons() {
        log.info("走的openapi");
        return couponService.listCoupons();
    }

}
