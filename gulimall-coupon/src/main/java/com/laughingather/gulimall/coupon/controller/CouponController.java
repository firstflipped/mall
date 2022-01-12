package com.laughingather.gulimall.coupon.controller;

import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.coupon.entity.CouponEntity;
import com.laughingather.gulimall.coupon.entity.query.CouponQuery;
import com.laughingather.gulimall.coupon.service.CouponService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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
@Api(tags = "优惠券模块")
public class CouponController {

    @Resource
    private CouponService couponService;

    @GetMapping("/list")
    @ApiOperation(value = "查询优惠券列表")
    public MyResult<List<CouponEntity>> listCoupons() {
        List<CouponEntity> couponList = couponService.listCoupons();
        return MyResult.success(couponList);
    }


    @GetMapping("/page")
    @ApiOperation(value = "分页查询优惠券列表")
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
