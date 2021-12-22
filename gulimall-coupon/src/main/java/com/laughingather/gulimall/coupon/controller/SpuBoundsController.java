package com.laughingather.gulimall.coupon.controller;

import com.laughingather.gulimall.coupon.service.SpuBoundsService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * 商品spu积分设置
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-12 11:49:52
 */
@RestController
@RequestMapping("/coupon/spu-bounds")
@Api(tags = "商品spu积分设置模块")
public class SpuBoundsController {

    @Resource
    private SpuBoundsService spuBoundsService;

}
