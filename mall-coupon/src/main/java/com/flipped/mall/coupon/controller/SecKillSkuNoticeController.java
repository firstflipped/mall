package com.flipped.mall.coupon.controller;

import com.flipped.mall.coupon.service.SecKillSkuNoticeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * 秒杀商品通知订阅
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@RestController
@RequestMapping("/coupon/sec-kill-sku-notice")
public class SecKillSkuNoticeController {

    @Resource
    private SecKillSkuNoticeService secKillSkuNoticeService;

}
