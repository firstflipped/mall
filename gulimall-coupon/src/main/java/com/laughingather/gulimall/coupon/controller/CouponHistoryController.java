package com.laughingather.gulimall.coupon.controller;

import com.laughingather.gulimall.coupon.service.CouponHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



/**
 * 优惠券领取历史记录
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-12 11:49:53
 */
@RestController
@RequestMapping("/coupon/couponhistory")
public class CouponHistoryController {
    @Resource
    private CouponHistoryService couponHistoryService;

}
