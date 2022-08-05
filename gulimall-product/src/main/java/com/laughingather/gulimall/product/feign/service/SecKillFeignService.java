package com.laughingather.gulimall.product.feign.service;

import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.product.feign.entity.SecKillSkuRedisDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 秒杀服务远程调用类
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@FeignClient("gulimall-seckill")
@RequestMapping("/gulimall-seckill/openapi/seckill")
public interface SecKillFeignService {

    /**
     * 获取sku秒杀信息
     *
     * @param skuId skuId
     * @return sku秒杀信息传输实体
     */
    @GetMapping("/sec-kill-sku/{sid}/info")
    MyResult<SecKillSkuRedisDTO> getSecKillSkuInfo(@PathVariable("sid") Long skuId);
}

