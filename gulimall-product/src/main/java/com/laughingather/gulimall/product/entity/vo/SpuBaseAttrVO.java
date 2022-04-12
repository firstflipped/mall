package com.laughingather.gulimall.product.entity.vo;

import lombok.Data;

/**
 * spu基本属性视图展示实体
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Data
public class SpuBaseAttrVO {

    /**
     * 属性名
     */
    private String attrName;

    /**
     * 属性值
     */
    private String attrValue;
}