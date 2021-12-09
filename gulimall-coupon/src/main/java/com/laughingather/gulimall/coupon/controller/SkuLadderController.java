package com.laughingather.gulimall.coupon.controller;

import com.laughingather.gulimall.coupon.service.SkuLadderService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * 商品阶梯价格
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-12 11:49:52
 */
@RestController
@RequestMapping("/coupon/skuladder")
public class SkuLadderController {
    @Resource
    private SkuLadderService skuLadderService;
}
