package com.laughingather.gulimall.product.entity.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;

import java.util.List;


/**
 * @author WangJie
 */

@Data
@ToString
public class CategoryTreeVO {


    /**
     * 分类id
     */
    private Long catId;
    /**
     * 分类名称
     */
    private String name;
    /**
     * 父分类id
     */
    private Long parentCid;
    /**
     * 层级
     */
    private Integer catLevel;
    /**
     * 是否显示[0-不显示，1显示]
     */
    private Integer showStatus;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 图标地址
     */
    private String icon;
    /**
     * 计量单位
     */
    private String productUnit;
    /**
     * 商品数量
     */
    private Integer productCount;

    /**
     * @JsonInclude 用来过滤字段在不同情况下是否显示
     * JsonInclude.Include.NON_EMPTY 表示不为空才显示
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<CategoryTreeVO> children;

}
