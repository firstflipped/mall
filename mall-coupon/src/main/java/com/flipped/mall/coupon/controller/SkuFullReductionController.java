package com.flipped.mall.coupon.controller;

import com.flipped.mall.coupon.service.SkuFullReductionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * 商品满减信息模块
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@RestController
@RequestMapping("/coupon/sku-full-reduction")
public class SkuFullReductionController {

    @Resource
    private SkuFullReductionService skuFullReductionService;


}
