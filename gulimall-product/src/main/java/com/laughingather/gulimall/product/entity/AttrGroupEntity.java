package com.laughingather.gulimall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.laughingather.gulimall.common.valid.AddGroup;
import com.laughingather.gulimall.common.valid.UpdateGroup;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;

/**
 * 属性分组
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-11 15:12:49
 */
@Data
@TableName("pms_attr_group")
public class AttrGroupEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 分组id
     */
    @TableId
    @NotNull(message = "修改属性分组id字段不能为空", groups = {UpdateGroup.class})
    @Null(message = "新增属性分组id字段必须为空", groups = {AddGroup.class})
    private Long attrGroupId;
    /**
     * 组名
     */
    @NotBlank(message = "属性分组名称字段不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String attrGroupName;
    /**
     * 排序
     */
    @NotNull(message = "属性分组排序字段不能为空", groups = {AddGroup.class, UpdateGroup.class})
    @Min(value = 0, message = "属性分组名称字段最小值不能小于0")
    private Integer sort;
    /**
     * 描述
     */
    private String descript;
    /**
     * 组图标
     */
    private String icon;
    /**
     * 所属分类id
     */
    @NotNull(message = "属性分组所属分类id字段不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private Long catalogId;

}
