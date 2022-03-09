package com.laughingather.gulimall.product.entity.vo;

import lombok.Data;

/**
 * 属性视图展示实体
 *
 * @author：laughingather
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
