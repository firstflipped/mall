package com.laughingather.gulimall.order.feign.service;

import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.order.feign.entity.SpuInfoTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 商品服务第三方调用类
 *
 * @author：laughingather
 * @create：2021-10-20 2021/10/20
 */
@FeignClient("gulimall-product")
@RequestMapping("/gulimall-product/openapi/product")
public interface ProductFeignService {

    /**
     * 根据skuId获取spu信息
     *
     * @param skuId
     * @return
     */
    @GetMapping("/spu-info")
    MyResult<SpuInfoTO> getSpuInfoBySkuId(@RequestParam("sid") Long skuId);

}

