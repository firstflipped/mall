package com.flipped.mall.ware.feign.service;

import com.flipped.mall.common.entity.api.MyResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 远程调用商品服务
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@FeignClient("mall-product")
public interface ProductFeignService {

    /**
     * 根据skuId获取sku名称
     *
     * @param skuId
     * @return
     */
    @GetMapping("/mall-product/openapi/product/{sid}/name")
    MyResult<String> getSkuNameBySkuId(@PathVariable("sid") Long skuId);

}
