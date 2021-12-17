package com.laughingather.gulimall.seckill.feign.service;

import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.seckill.feign.entity.SkuInfoTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 商品服务第三方调用类
 *
 * @author：laughingather
 * @create：2021-11-15 2021/11/15
 */
@FeignClient("gulimall-product")
@RequestMapping("/gulimall-product/openapi/product")
public interface ProductFeignService {


    /**
     * 根据skuId获取sku详情信息
     *
     * @param skuId
     * @return
     */
    @GetMapping("/{sid}/info")
    MyResult<SkuInfoTO> getSkuInfoBySkuId(@PathVariable("sid") Long skuId);

}
