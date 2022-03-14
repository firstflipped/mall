package com.laughingather.gulimall.product.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.laughingather.gulimall.product.entity.SpuImagesEntity;

/**
 * spu图片
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-11 15:12:49
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
