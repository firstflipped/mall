package com.laughingather.gulimall.product.entity.vo;

import lombok.Data;

/**
 * 属性值对应skuId视图展示实体
 *
 * @author：laughingather
 * @create：2021-06-08 21:18
 */
@Data
public class AttrValueWithSkuIdVO {

    /**
     * 属性值
     */
    private String attrValue;

    /**
     * skuId集合
     */
    private String skuIds;

}
