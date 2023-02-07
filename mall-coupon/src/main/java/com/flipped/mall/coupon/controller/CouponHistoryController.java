package com.flipped.mall.coupon.controller;

import com.flipped.mall.coupon.service.CouponHistoryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * 优惠券领取历史记录路由
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@RestController
@RequestMapping("/coupon/coupon-history")
public class CouponHistoryController {

    @Resource
    private CouponHistoryService couponHistoryService;

}
