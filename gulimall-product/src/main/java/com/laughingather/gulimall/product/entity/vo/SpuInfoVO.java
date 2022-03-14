package com.laughingather.gulimall.product.entity.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * spu信息视图展示实体
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-11 15:12:49
 */
@Data
public class SpuInfoVO {

    /**
     * 商品id
     */
    private Long id;

    /**
     * 商品名称
     */
    private String spuName;

    /**
     * 上架状态
     */
    private Integer publishStatus;

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

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 创建时间
     */
    private LocalDateTime updateTime;

}
