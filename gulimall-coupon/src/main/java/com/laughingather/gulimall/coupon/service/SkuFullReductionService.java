package com.laughingather.gulimall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laughingather.gulimall.coupon.entity.SkuFullReductionEntity;
import com.laughingather.gulimall.coupon.entity.to.SkuOtherInfoTO;

/**
 * 商品满减信息
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-12 11:49:53
 */
public interface SkuFullReductionService extends IService<SkuFullReductionEntity> {

    void saveSkuFullReduction(SkuOtherInfoTO skuOtherInfoTO);
}

