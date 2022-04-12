/**
 * Copyright 2021 json.cn
 */
package com.laughingather.gulimall.product.entity.param;

import lombok.Data;

import java.util.List;

/**
 * spu前端传入实体
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Data
public class SpuParam {
    /**
     * spu名称
     */
    private String spuName;

    /**
     * 分类id
     */
    private Long categoryId;

    /**
     * 品牌id
     */
    private Long brandId;

    /**
     * 上架状态
     */
    private Integer publishStatus;

    /**
     * 商品介绍，图片集
     */
    private List<String> spuDescription;

    /**
     * 图片集
     */
    private List<String> images;

    /**
     * 积分信息
     */
    private SpuBoundParam bounds;

    /**
     * 基本属性信息
     */
    private List<SpuBaseAttrParam> baseAttrs;

    /**
     * sku信息
     */
    private List<SkuParam> skus;
}