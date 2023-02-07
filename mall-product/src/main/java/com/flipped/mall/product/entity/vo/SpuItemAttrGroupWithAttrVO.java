package com.flipped.mall.product.entity.vo;

import lombok.Data;

import java.util.List;

/**
 * spu项属性分组及属性视图展示实体
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Data
public class SpuItemAttrGroupWithAttrVO {

    /**
     * 属性分组名称
     */
    private String attrGroupName;

    /**
     * 属性列表
     */
    private List<SpuBaseAttrVO> attrs;
}