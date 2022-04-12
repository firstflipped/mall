package com.laughingather.gulimall.product.entity.vo;

import lombok.Data;

/**
 * 属性值对应skuId视图展示实体
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
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
