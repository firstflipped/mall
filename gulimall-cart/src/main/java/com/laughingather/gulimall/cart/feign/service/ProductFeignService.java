package com.laughingather.gulimall.cart.feign.service;

import com.laughingather.gulimall.cart.feign.entity.SkuInfoEntity;
import com.laughingather.gulimall.common.api.MyResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 商品远程开放接口
 *
 * @author：laughingather
 * @create：2021-08-23 2021/8/23
 */
@FeignClient("gulimall-product")
public interface ProductFeignService {

    /**
     * 调用远程接口获取商品详情
     *
     * @param skuId
     * @return
     */
    @GetMapping("/{skuId}/info")
    MyResult<SkuInfoEntity> getSkuInfoBySkuId(@PathVariable("skuId") Long skuId);
}
