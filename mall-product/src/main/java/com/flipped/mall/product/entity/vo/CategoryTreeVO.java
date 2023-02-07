package com.flipped.mall.product.entity.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;

import java.util.List;


/**
 * 分类树形结构视图展示实体
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Data
@ToString
public class CategoryTreeVO {
    /**
     * 分类id
     */
    private Long categoryId;

    /**
     * 分类名称
     */
    private String categoryName;

    /**
     * 父分类id
     */
    private Long parentId;

    /**
     * 层级
     */
    private Integer categoryLevel;

    /**
     * 是否显示[0-不显示，1显示]
     */
    private Integer showStatus;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * @JsonInclude 用来过滤字段在不同情况下是否显示
     * JsonInclude.Include.NON_EMPTY 表示不为空才显示
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<CategoryTreeVO> children;

}
