package com.laughingather.gulimall.coupon.service;

import com.laughingather.gulimall.coupon.entity.dto.SkuOtherInfoDTO;

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
     * @param skuOtherInfoDTO sku其他信息
     */
    void saveSkuOtherInfo(SkuOtherInfoDTO skuOtherInfoDTO);
}
