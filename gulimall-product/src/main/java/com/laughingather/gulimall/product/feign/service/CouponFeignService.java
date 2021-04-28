package com.laughingather.gulimall.product.feign.service;

import com.laughingather.gulimall.product.entity.to.SkuOtherInfoTO;
import com.laughingather.gulimall.product.entity.to.SpuBoundTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("gulimall-coupon")
public interface CouponFeignService {

    /**
     * 调用远程服务其实就相当于根据服务名 + 路由发送一个对应服务的请求而已
     * 只要参数的属性能够兼容，没必要参数签名完全一致
     *
     * @param spuBoundTO
     */
    @PostMapping("/gulimall-coupon/openapi/coupon/spubounds")
    Boolean saveSpuBounds(@RequestBody SpuBoundTO spuBoundTO);

    @PostMapping("/gulimall-coupon/openapi/coupon/skuOtherInfo")
    Boolean saveSkuOtherInfo(@RequestBody SkuOtherInfoTO skuOtherInfoTO);


}

