package com.flipped.mall.coupon.controller;

import com.flipped.mall.coupon.service.SpuBoundsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * 商品spu积分设置
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@RestController
@RequestMapping("/coupon/spu-bounds")
public class SpuBoundsController {

    @Resource
    private SpuBoundsService spuBoundsService;

}
