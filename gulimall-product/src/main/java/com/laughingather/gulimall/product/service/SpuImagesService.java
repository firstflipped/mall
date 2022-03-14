package com.laughingather.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laughingather.gulimall.product.entity.SpuImagesEntity;

/**
 * spu图片逻辑接口
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-11 15:12:49
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

