package com.flipped.mall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.flipped.mall.coupon.entity.SkuFullReductionEntity;
import com.flipped.mall.coupon.entity.dto.SkuOtherInfoDTO;

/**
 * 商品满减信息
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
public interface SkuFullReductionService extends IService<SkuFullReductionEntity> {

    /**
     * 保存商品满减信息
     *
     * @param skuOtherInfoDTO 商品满减信息
     */
    void saveSkuFullReduction(SkuOtherInfoDTO skuOtherInfoDTO);
}

