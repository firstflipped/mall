package com.laughingather.gulimall.seckill.feign.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * sku详情
 *
 * @author：laughingather
 * @create：2021-11-15 2021/11/15
 */
@Data
public class SkuInfoTO {
    private Long skuId;

    /**
     * spuId
     */
    private Long spuId;
    /**
     * sku名称
     */
    private String skuName;
    /**
     * sku介绍描述
     */
    private String skuDesc;
    /**
     * 所属分类id
     */
    private Long catalogId;
    /**
     * 品牌id
     */
    private Long brandId;
    /**
     * 默认图片
     */
    private String skuDefaultImg;
    /**
     * 标题
     */
    private String skuTitle;
    /**
     * 副标题
     */
    private String skuSubtitle;
    /**
     * 价格
     */
    private BigDecimal price;
    /**
     * 销量
     */
    private Long saleCount;

}

