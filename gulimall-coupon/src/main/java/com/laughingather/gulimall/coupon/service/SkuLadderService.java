package com.laughingather.gulimall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laughingather.gulimall.coupon.entity.SkuLadderEntity;
import com.laughingather.gulimall.coupon.entity.to.SkuOtherInfoTO;

/**
 * 商品阶梯价格
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-12 11:49:52
 */
public interface SkuLadderService extends IService<SkuLadderEntity> {

    void saveSkuLadder(SkuOtherInfoTO skuOtherInfoTO);
}

