package com.laughingather.gulimall.ware.feign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("gulimall-product")
public interface ProductFeignService {

    @GetMapping("/gulimall-product/openapi/product/skuInfo/getSkuName")
    String getSkuNameBuSkuId(@RequestParam("skuId") Long skuId);

}