package com.laughingather.gulimall.coupon.controller;

import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.coupon.entity.CouponEntity;
import com.laughingather.gulimall.coupon.entity.query.CouponQuery;
import com.laughingather.gulimall.coupon.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
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
    public MyResult<List<CouponEntity>> listCoupons() {
        List<CouponEntity> couponList = couponService.listCoupons();
        return MyResult.success(couponList);
    }


    @GetMapping("/page")
    public MyResult<MyPage<CouponEntity>> pageCoupons(@ModelAttribute CouponQuery couponQuery) {
        MyPage<CouponEntity> couponPage = couponService.pageCoupons(couponQuery);
        return MyResult.success(couponPage);
    }

    @PostMapping
    public MyResult saveCoupon(@RequestBody CouponEntity coupon) {
        couponService.save(coupon);
        return MyResult.success();
    }

    @PutMapping
    public MyResult updateCoupon(@RequestBody CouponEntity coupon) {
        couponService.updateById(coupon);
        return MyResult.success();
    }


    @DeleteMapping
    public MyResult deleteCouponByIds(@RequestBody Long[] ids) {
        couponService.removeByIds(Arrays.asList(ids));
        return MyResult.success();
    }

}
