package com.laughingather.gulimall.coupon.service;

import com.laughingather.gulimall.coupon.entity.to.SkuOtherInfoTO;

/**
 *
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
public interface CouponOpenService {

    /**
     * 保存sku其他信息
     *
     * @param skuOtherInfoTO
     */
    void saveSkuOtherInfo(SkuOtherInfoTO skuOtherInfoTO);
}
