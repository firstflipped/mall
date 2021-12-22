package com.laughingather.gulimall.coupon.controller;

import com.laughingather.gulimall.coupon.service.SkuFullReductionService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * 商品满减信息
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-12 11:49:53
 */
@RestController
@RequestMapping("/coupon/sku-full-reduction")
@Api(tags = "商品满减信息模块")
public class SkuFullReductionController {

    @Resource
    private SkuFullReductionService skuFullReductionService;


}
