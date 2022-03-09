package com.laughingather.gulimall.product.entity.vo;

import lombok.Data;

import java.util.List;

/**
 * spu项属性分组及属性视图展示实体
 *
 * @author：laughingather
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