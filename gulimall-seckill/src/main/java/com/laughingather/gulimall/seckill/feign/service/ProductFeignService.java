package com.laughingather.gulimall.seckill.feign.service;

import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.seckill.feign.entity.SkuInfoTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 商品服务第三方调用类
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@FeignClient("gulimall-product")
public interface ProductFeignService {


    /**
     * 根据skuId获取sku详情信息
     *
     * @param skuId
     * @return
     */
    @GetMapping("/gulimall-product/openapi/product/{sid}/info")
    MyResult<SkuInfoTO> getSkuInfoBySkuId(@PathVariable("sid") Long skuId);

}
