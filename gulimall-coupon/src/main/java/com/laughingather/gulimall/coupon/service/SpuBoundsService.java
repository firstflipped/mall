package com.laughingather.gulimall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laughingather.gulimall.coupon.entity.SpuBoundsEntity;
import com.laughingather.gulimall.coupon.entity.to.SpuBoundTO;

/**
 * 商品spu积分设置
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
public interface SpuBoundsService extends IService<SpuBoundsEntity> {

    void saveSpuBounds(SpuBoundTO spuBoundTO);
}

