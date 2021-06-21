package com.laughingather.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laughingather.gulimall.product.entity.SkuImagesEntity;

import java.util.List;

/**
 * sku图片
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-11 15:12:49
 */
public interface SkuImagesService extends IService<SkuImagesEntity> {

    /**
     * 根据skuId获取
     *
     * @param skuId
     * @return
     */
    List<SkuImagesEntity> listImagesBySkuId(Long skuId);
}

