package com.laughingather.gulimall.product.entity.vo;

import lombok.Data;

@Data
public class AttrGroupVO {

    /**
     * 分组id
     */
    private Long attrGroupId;

    /**
     * 组名
     */
    private String attrGroupName;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 描述
     */
    private String description;

    /**
     * 组图标
     */
    private String icon;

    /**
     * 所属分类id
     */
    private Long categoryId;

    /**
     * 所属分类名称
     */
    private Long categoryName;

    /**
     * 所属分类完整路径
     */
    private Long[] categoryPath;

}
