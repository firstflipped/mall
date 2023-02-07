package com.flipped.mall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.flipped.mall.product.entity.SpuImagesEntity;

/**
 * spu图片逻辑接口
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
public interface SpuImagesService extends IService<SpuImagesEntity> {

    /**
     * 获取spu默认图片
     *
     * @param spuId spuId
     * @return spu默认图片地址
     */
    String getDefaultImage(Long spuId);

}

