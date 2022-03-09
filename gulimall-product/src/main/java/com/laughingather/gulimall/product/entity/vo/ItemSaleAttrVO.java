package com.laughingather.gulimall.product.entity.vo;

import lombok.Data;

import java.util.List;

/**
 * 项销售属性视图展示实体
 *
 * @author：laughingather
 */
@Data
public class ItemSaleAttrVO {

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
    private List<AttrValueWithSkuIdVO> attrValues;
}