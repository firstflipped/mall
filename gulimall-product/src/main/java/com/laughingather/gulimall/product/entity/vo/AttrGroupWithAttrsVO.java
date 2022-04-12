package com.laughingather.gulimall.product.entity.vo;

import com.laughingather.gulimall.product.entity.AttrEntity;
import lombok.Data;

import java.util.List;


/**
 * 属性分组&关联属性视图展示实体
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Data
public class AttrGroupWithAttrsVO {
    private static final long serialVersionUID = 1L;

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

    private List<AttrEntity> attrs;
}
