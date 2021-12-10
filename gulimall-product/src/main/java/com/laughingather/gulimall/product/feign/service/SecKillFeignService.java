package com.laughingather.gulimall.product.feign.service;

import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.product.feign.entity.SecKillSkuRedisTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 秒杀服务远程调用类
 *
 * @author：laughingather
 * @create：2021-11-17 2021/11/17
 */
@FeignClient("gulimall-seckill")
@RequestMapping("/gulimall-seckill/openapi/seckill")
public interface SecKillFeignService {

    @GetMapping("/sec-kill-sku/{sid}/info")
    MyResult<SecKillSkuRedisTO> getSecKillSkuInfo(@PathVariable("sid") Long skuId);
}

