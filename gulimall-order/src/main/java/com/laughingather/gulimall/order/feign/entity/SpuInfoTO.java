package com.laughingather.gulimall.order.feign.entity;

import lombok.Data;

/**
 * spu信息
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-11 15:12:49
 */
@Data
public class SpuInfoTO {

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
     * 品牌名称
     */
    private String brandName;

    /**
     * 图片
     */
    private String image;

}
