package com.laughingather.gulimall.product.feign.service;

import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.product.entity.to.SkuOtherInfoTO;
import com.laughingather.gulimall.product.entity.to.SpuBoundTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 优惠服务第三方调用接口
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@FeignClient("gulimall-coupon")
public interface CouponFeignService {

    /**
     * 调用远程服务其实就相当于根据服务名 + 路由发送一个对应服务的请求而已
     * 只要参数的属性能够兼容，没必要参数签名完全一致
     *
     * @param spuBoundTO spu积分传输实体
     */
    @PostMapping("/gulimall-coupon/openapi/coupon/spu-bounds")
    MyResult<Void> saveSpuBounds(@RequestBody SpuBoundTO spuBoundTO);

    /**
     * 保存sku的其他信息
     *
     * @param skuOtherInfoTO sku其他信息传输实体
     */
    @PostMapping("/gulimall-coupon/openapi/coupon/sku-other-info")
    MyResult<Void> saveSkuOtherInfo(@RequestBody SkuOtherInfoTO skuOtherInfoTO);

}

