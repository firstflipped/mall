package com.flipped.mall.coupon.controller;

import com.flipped.mall.coupon.service.CouponSpuRelationService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * 优惠券与产品关联路由
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@RestController
@RequestMapping("/coupon/coupon-spu-relation")
public class CouponSpuRelationController {

    @Resource
    private CouponSpuRelationService couponSpuRelationService;


}
