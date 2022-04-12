package com.laughingather.gulimall.product.entity.param;

import lombok.Data;

/**
 * sku基础属性传入实体
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Data
public class SpuBaseAttrParam {
    /**
     * 属性id
     */
    private Long attrId;

    /**
     * 属性值集合
     */
    private String attrValues;

    /**
     * 是否快速展示
     */
    private Integer quickShow;
}