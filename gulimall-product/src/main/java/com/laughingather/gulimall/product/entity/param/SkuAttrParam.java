package com.laughingather.gulimall.product.entity.param;

import lombok.Data;

@Data
public class SkuAttrParam {
    /**
     * 属性id
     */
    private Long attrId;

    /**
     * 属性名称
     */
    private String attrName;

    /**
     * 属性值
     */
    private String attrValue;
}