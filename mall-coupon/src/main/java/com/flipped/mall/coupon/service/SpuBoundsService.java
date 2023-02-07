package com.flipped.mall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.flipped.mall.coupon.entity.SpuBoundsEntity;
import com.flipped.mall.coupon.entity.dto.SpuBoundDTO;

/**
 * 商品spu积分设置
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
public interface SpuBoundsService extends IService<SpuBoundsEntity> {

    /**
     * 保存spu积分信息
     *
     * @param spuBoundDTO spu积分信息
     */
    void saveSpuBounds(SpuBoundDTO spuBoundDTO);
}

