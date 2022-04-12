package com.laughingather.gulimall.product.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.laughingather.gulimall.product.entity.SpuImagesEntity;

/**
 * spu图片
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
public interface SpuImagesDao extends BaseMapper<SpuImagesEntity> {

    /**
     * 获取spu默认图片地址
     *
     * @param spuId spuId
     * @return spu默认图片地址
     */
    String getDefaultImage(Long spuId);
}
