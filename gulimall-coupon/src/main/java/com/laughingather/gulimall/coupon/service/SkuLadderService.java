package com.laughingather.gulimall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laughingather.gulimall.coupon.entity.SkuLadderEntity;
import com.laughingather.gulimall.coupon.entity.to.SkuOtherInfoTO;

/**
 * 商品阶梯价格
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
public interface SkuLadderService extends IService<SkuLadderEntity> {

    void saveSkuLadder(SkuOtherInfoTO skuOtherInfoTO);
}

