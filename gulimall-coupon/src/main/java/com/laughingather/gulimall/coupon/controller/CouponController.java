package com.laughingather.gulimall.coupon.controller;

import com.laughingather.gulimall.common.entity.api.MyPage;
import com.laughingather.gulimall.common.entity.api.MyResult;
import com.laughingather.gulimall.coupon.entity.CouponEntity;
import com.laughingather.gulimall.coupon.entity.query.CouponQuery;
import com.laughingather.gulimall.coupon.service.CouponService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;


/**
 * 优惠券信息路由
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@RestController
@RequestMapping("/coupon/coupon")
@Tag(name = "优惠券模块")
public class CouponController {

    @Resource
    private CouponService couponService;

    @GetMapping("/list")
    @Operation(summary = "查询优惠券列表")
    public MyResult<List<CouponEntity>> listCoupons() {
        List<CouponEntity> couponList = couponService.listCoupons();
        return MyResult.success(couponList);
    }


    @GetMapping("/page")
    @Operation(summary = "分页查询优惠券列表")
    public MyResult<MyPage<CouponEntity>> listCouponsWithPage(@ModelAttribute CouponQuery couponQuery) {
        MyPage<CouponEntity> couponPage = couponService.listCouponsWithPage(couponQuery);
        return MyResult.success(couponPage);
    }

    @PostMapping
    public MyResult<Void> saveCoupon(@RequestBody CouponEntity coupon) {
        couponService.save(coupon);
        return MyResult.success();
    }

    @PutMapping
    public MyResult<Void> updateCoupon(@RequestBody CouponEntity coupon) {
        couponService.updateById(coupon);
        return MyResult.success();
    }


    @DeleteMapping
    public MyResult<Void> deleteCouponByIds(@RequestBody Long[] ids) {
        couponService.removeByIds(Arrays.asList(ids));
        return MyResult.success();
    }

}
