package com.laughingather.gulimall.product.entity.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 属性分组视图展示实体
 *
 * @author：laughingather
 */
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
    private String categoryName;

    /**
     * 所属分类完整路径
     */
    private Long[] categoryPath;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

}
