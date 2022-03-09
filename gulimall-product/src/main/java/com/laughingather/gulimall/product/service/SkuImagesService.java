package com.laughingather.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laughingather.gulimall.product.entity.SkuImagesEntity;

import java.util.List;

/**
 * sku图片管理逻辑接口
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-11 15:12:49
 */
public interface SkuImagesService extends IService<SkuImagesEntity> {

    /**
     * 根据skuId获取图片集合
     *
     * @param skuId skuId
     * @return sku图片列表
     */
    List<SkuImagesEntity> listImagesBySkuId(Long skuId);
}

