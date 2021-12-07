package com.laughingather.gulimall.coupon.controller;

import com.laughingather.gulimall.coupon.service.CouponSpuCategoryRelationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * 优惠券分类关联
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-12 11:49:52
 */
@RestController
@RequestMapping("/coupon/couponspucategoryrelation")
public class CouponSpuCategoryRelationController {

    @Resource
    private CouponSpuCategoryRelationService couponSpuCategoryRelationService;

}
