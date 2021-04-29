package com.laughingather.gulimall.product.openapi;

import com.laughingather.gulimall.product.entity.SkuInfoEntity;
import com.laughingather.gulimall.product.service.SkuInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/openapi/product")
public class ProductOpenApi {

    @Resource
    private SkuInfoService skuInfoService;

    @GetMapping("/skuInfo/getSkuName")
    public String getSkuNameBuSkuId(@RequestParam("skuId") Long skuId) {
        SkuInfoEntity byId = skuInfoService.getById(skuId);
        if (byId != null) {
            return byId.getSkuName();
        }
        return "";
    }

}