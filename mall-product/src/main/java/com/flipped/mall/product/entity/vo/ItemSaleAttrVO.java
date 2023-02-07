package com.flipped.mall.product.entity.vo;

import lombok.Data;

import java.util.List;

/**
 * 项销售属性视图展示实体
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
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