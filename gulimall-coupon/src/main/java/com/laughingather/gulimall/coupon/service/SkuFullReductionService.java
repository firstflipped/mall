package com.laughingather.gulimall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laughingather.gulimall.coupon.entity.SkuFullReductionEntity;
import com.laughingather.gulimall.coupon.entity.to.SkuOtherInfoTO;

/**
 * 商品满减信息
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
public interface SkuFullReductionService extends IService<SkuFullReductionEntity> {

    void saveSkuFullReduction(SkuOtherInfoTO skuOtherInfoTO);
}

