package com.laughingather.gulimall.cart.feign.service;

import com.laughingather.gulimall.cart.feign.entity.SkuInfoTO;
import com.laughingather.gulimall.common.api.MyResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.List;

/**
 * 商品服务第三方调用接口
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@FeignClient("gulimall-product")
@RequestMapping("/gulimall-product/openapi/product")
public interface ProductFeignService {

    /**
     * 调用远程接口获取商品价格
     *
     * @param skuId
     * @return
     */
    @GetMapping("/{sid}/price")
    MyResult<BigDecimal> getSkuPriceBySkuId(@PathVariable("sid") Long skuId);

    /**
     * 调用远程接口获取商品详情
     *
     * @param skuId
     * @return
     */
    @GetMapping("/{sid}/info")
    MyResult<SkuInfoTO> getSkuInfoBySkuId(@PathVariable("sid") Long skuId);

    /**
     * 调用远程端口获取销售属性列表
     *
     * @param skuId
     * @return
     */
    @GetMapping("/sku-sale-attr-value/list/{sid}")
    MyResult<List<String>> getSkuSaleAttrValues(@PathVariable("sid") Long skuId);

}
