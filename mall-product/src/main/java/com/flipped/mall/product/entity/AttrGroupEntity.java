package com.flipped.mall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.flipped.mall.common.valid.AddGroup;
import com.flipped.mall.common.valid.UpdateGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 属性分组实体
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Data
@TableName("pms_attr_group")
@Schema(name = "属性分组实体")
public class AttrGroupEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 分组id
     */
    @TableId
    @Schema(name = "属性分组id")
    @NotNull(message = "修改属性分组id字段不能为空", groups = {UpdateGroup.class})
    @Null(message = "新增属性分组id字段必须为空", groups = {AddGroup.class})
    private Long attrGroupId;

    /**
     * 组名
     */
    @Schema(name = "属性分组名称")
    @NotBlank(message = "属性分组名称字段不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String attrGroupName;

    /**
     * 排序
     */
    @Schema(name = "属性分组排序")
    @NotNull(message = "属性分组排序字段不能为空", groups = {AddGroup.class, UpdateGroup.class})
    @Min(value = 0, message = "属性分组名称字段最小值不能小于0")
    private Integer sort;

    /**
     * 描述
     */
    @Schema(name = "描述")
    private String description;

    /**
     * 所属分类id
     */
    @Schema(name = "属性分组所属分类id")
    @NotNull(message = "属性分组所属分类id字段不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private Long categoryId;

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
