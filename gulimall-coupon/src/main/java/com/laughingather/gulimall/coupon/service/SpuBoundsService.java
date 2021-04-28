package com.laughingather.gulimall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laughingather.gulimall.coupon.entity.SpuBoundsEntity;
import com.laughingather.gulimall.coupon.entity.to.SpuBoundTO;

/**
 * 商品spu积分设置
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-12 11:49:52
 */
public interface SpuBoundsService extends IService<SpuBoundsEntity> {

    void saveSpuBounds(SpuBoundTO spuBoundTO);
}

