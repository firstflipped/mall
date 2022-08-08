package com.laughingather.gulimall.order.feign.entity;

import lombok.Data;

/**
 * spu信息
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Data
public class SpuInfoDTO {

    /**
     * 商品id
     */
    private Long id;

    /**
     * 商品名称
     */
    private String spuName;

    /**
     * 所属分类id
     */
    private Long categoryId;

    /**
     * 所属分类id
     */
    private String categoryName;

    /**
     * 品牌id
     */
    private Long brandId;

    /**
     * 品牌名称
     */
    private String brandName;

    /**
     * 图片
     */
    private String image;

}
