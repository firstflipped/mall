package com.laughingather.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laughingather.gulimall.product.entity.SkuImagesEntity;

import java.util.List;

/**
 * sku图片管理逻辑接口
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
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

