package com.laughingather.gulimall.ware.feign.service;

import com.laughingather.gulimall.common.api.MyResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 远程调用商品服务
 *
 * @author：laughingather
 * @create：2021-10-18 2021/10/18
 */
@FeignClient("gulimall-product")
public interface ProductFeignService {

    /**
     * 根据skuId获取sku名称
     *
     * @param skuId
     * @return
     */
    @GetMapping("/gulimall-product/openapi/product/{sid}/name")
    MyResult<String> getSkuNameBySkuId(@PathVariable("sid") Long skuId);

}
