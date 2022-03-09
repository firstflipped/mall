package com.laughingather.gulimall.product.entity.param;

import lombok.Data;

/**
 * sku基础属性传入实体
 *
 * @author laughingather
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