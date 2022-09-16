package com.laughingather.gulimall.coupon.controller;

import com.laughingather.gulimall.coupon.service.SkuFullReductionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * 商品满减信息
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@RestController
@RequestMapping("/coupon/sku-full-reduction")
@Tag(name = "商品满减信息模块")
public class SkuFullReductionController {

    @Resource
    private SkuFullReductionService skuFullReductionService;


}
