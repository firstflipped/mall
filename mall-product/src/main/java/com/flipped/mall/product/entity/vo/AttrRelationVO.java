package com.flipped.mall.product.entity.vo;

import lombok.Data;

/**
 * 属性视图展示实体
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Data
public class AttrRelationVO {

    /**
     * 属性名
     */
    private String attrName;

    /**
     * 可选值列表[用逗号分隔]
     */
    private String valueSelect;

}
