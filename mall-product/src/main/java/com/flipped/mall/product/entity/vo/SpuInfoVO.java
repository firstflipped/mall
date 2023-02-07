package com.flipped.mall.product.entity.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * spu信息视图展示实体
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
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
