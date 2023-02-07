package com.flipped.mall.coupon.controller;

import com.flipped.mall.coupon.service.CouponSpuCategoryRelationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * 优惠券分类关联路由
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@RestController
@RequestMapping("/coupon/coupon-spu-category-relation")
public class CouponSpuCategoryRelationController {

    @Resource
    private CouponSpuCategoryRelationService couponSpuCategoryRelationService;

}
