package com.flipped.mall.coupon.controller;

import com.flipped.mall.common.entity.api.MyPage;
import com.flipped.mall.common.entity.api.MyResult;
import com.flipped.mall.coupon.entity.CouponEntity;
import com.flipped.mall.coupon.entity.query.CouponQuery;
import com.flipped.mall.coupon.service.CouponService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;


/**
 * 优惠券模块
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@RestController
@RequestMapping("/coupon/coupon")
public class CouponController {

    @Resource
    private CouponService couponService;

    @GetMapping("/list")
    public MyResult<List<CouponEntity>> listCoupons() {
        List<CouponEntity> couponList = couponService.listCoupons();
        return MyResult.success(couponList);
    }


    @GetMapping("/page")
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
