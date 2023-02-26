package com.flipped.mall.coupon.controller;

import com.flipped.mall.coupon.service.SkuLadderService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * 商品阶梯价格
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@RestController
@RequestMapping("/coupon/sku-ladder")
public class SkuLadderController {

    @Resource
    private SkuLadderService skuLadderService;

}
