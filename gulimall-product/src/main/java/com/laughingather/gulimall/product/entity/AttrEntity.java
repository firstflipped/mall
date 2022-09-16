package com.laughingather.gulimall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 商品属性实体
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Data
@TableName("pms_attr")
@Schema(name = "属性实体")
public class AttrEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 属性id
     */
    @TableId
    @Schema(name = "属性id")
    private Long attrId;

    /**
     * 属性名
     */
    @Schema(name = "属性名")
    private String attrName;

    /**
     * 是否需要检索[0-不需要，1-需要]
     */
    @Schema(name = "是否需要检索[0-不需要，1-需要]")
    private Integer searchType;

    /**
     * 可选值列表[用逗号分隔]
     */
    @Schema(name = "可选值列表[用逗号分隔]")
    private String valueSelect;

    /**
     * 属性类型[0-销售属性，1-基本属性，2-既是销售属性又是基本属性]
     */
    @Schema(name = "属性类型[0-销售属性，1-基本属性，2-既是销售属性又是基本属性]")
    private Integer attrType;

    /**
     * 启用状态[0 - 禁用，1 - 启用]
     */
    @Schema(name = "启用状态[0 - 禁用，1 - 启用]")
    private Integer enable;


    /**
     * 快速展示[是否展示在介绍上；0-否 1-是]，在sku中仍然可以调整
     */
    @Schema(name = "快速展示[是否展示在介绍上；0-否 1-是]，在sku中仍然可以调整")
    private Integer quickShow;

    /**
     * 所属分类
     */
    @Schema(name = "所属分类")
    private Long categoryId;

    /**
     * 所属分类
     */
    @Schema(name = "所属属性分组")
    private Long attrGroupId;

    /**
     * 创建时间
     */
    @Schema(name = "创建时间")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @Schema(name = "更新时间")
    private LocalDateTime updateTime;

}
