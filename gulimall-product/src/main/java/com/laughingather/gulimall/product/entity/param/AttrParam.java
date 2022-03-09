package com.laughingather.gulimall.product.entity.param;

import com.laughingather.gulimall.common.valid.AddGroup;
import com.laughingather.gulimall.common.valid.UpdateGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.List;

/**
 * 属性前端输入实体
 *
 * @author laughingather
 */
@Data
@ApiModel(value = "属性前端传入实体")
public class AttrParam {

    @ApiModelProperty(value = "属性id")
    @Null(message = "属性新增id必须为空", groups = {AddGroup.class})
    @NotNull(message = "属性修改id不能为空", groups = {UpdateGroup.class})
    private Long attrId;

    /**
     * 属性名
     */
    @ApiModelProperty(value = "属性名称")
    @NotBlank(message = "属性名称不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String attrName;

    /**
     * 属性图标
     */
    @ApiModelProperty(value = "属性图标")
    private String icon;

    /**
     * 可选值列表[用逗号分隔]
     */
    @ApiModelProperty(value = "可选值列表")
    @NotEmpty(message = "属性可选值列表不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private List<String> valueSelect;

    /**
     * 属性类型[0-销售属性，1-基本属性，2-既是销售属性又是基本属性]
     */
    @ApiModelProperty(value = "属性类型[0-销售属性，1-基本属性，2-既是销售属性又是基本属性]")
    @NotNull(message = "属性类型不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private Integer attrType;

    /**
     * 启用状态[0 - 禁用，1 - 启用]
     */
    @ApiModelProperty(value = "启用状态[0 - 禁用，1 - 启用]")
    @NotNull(message = "启用状态不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private Integer enable;

    /**
     * 是否需要检索[0-不需要，1-需要]
     */
    @ApiModelProperty(value = "是否需要检索[0-不需要，1-需要]")
    @NotNull(message = "是否开启检索不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private Integer searchType;

    /**
     * 快速展示【是否展示在介绍上；0-否 1-是】，在sku中仍然可以调整
     */
    @ApiModelProperty(value = "快速展示【是否展示在介绍上；0-否 1-是】，在sku中仍然可以调整")
    @NotNull(message = "是否开启快速展示不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private Integer quickShow;

    /**
     * 所属分类
     */
    @ApiModelProperty(value = "属性所属分类")
    @NotNull(message = "属性所属分类不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private Long categoryId;

    @ApiModelProperty(value = "属性所属分组")
    @NotNull(message = "属性所属分组不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private Long attrGroupId;

}
