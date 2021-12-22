package com.laughingather.gulimall.coupon.controller;

import com.laughingather.gulimall.coupon.service.SecKillSkuNoticeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * 秒杀商品通知订阅
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-12 11:49:52
 */
@RestController
@RequestMapping("/coupon/sec-kill-sku-notice")
public class SecKillSkuNoticeController {

    @Resource
    private SecKillSkuNoticeService secKillSkuNoticeService;

}
