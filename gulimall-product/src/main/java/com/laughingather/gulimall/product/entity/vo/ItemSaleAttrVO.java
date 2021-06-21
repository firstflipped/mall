package com.laughingather.gulimall.product.entity.vo;

import lombok.Data;

import java.util.List;

/**
 * @author WangJie
 */
@Data
public class ItemSaleAttrVO {
    private Long attrId;
    private String attrName;
    private List<AttrValueWithSkuIdVO> attrValues;
}