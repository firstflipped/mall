package com.flipped.mall.admin.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.laughingather.gulimall.common.valid.AddGroup;
import com.laughingather.gulimall.common.valid.ListValue;
import com.laughingather.gulimall.common.valid.UpdateGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.time.LocalDateTime;

/**
 * 字典实体
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2023-02-03 14:11:07
 */
@Schema
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DictEntity {
    /**
     * 字典id
     */
    @TableId
    @Schema(description = "字典id")
    @NotNull(message = "修改时，字典id不能为空", groups = UpdateGroup.class)
    @Null(message = "新增时，字典id必须为空", groups = UpdateGroup.class)
    private Long dictId;

    /**
     * 字典编码
     */
    @Schema(description = "字典编码")
    @NotBlank(message = "字典编码不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String dictCode;

    /**
     * 字典名称
     */
    @Schema(description = "字典名称")
    @NotBlank(message = "字典名称不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String dictName;

    /**
     * 是否启用
     */
    @Schema(description = "是否启用")
    @NotNull(message = "是否启用状态不能为空", groups = {AddGroup.class, UpdateGroup.class})
    @ListValue(message = "启用状态值只能为1、0", values = {1, 0}, groups = {AddGroup.class, UpdateGroup.class})
    private Integer enable;

    /**
     * 排序
     */
    @Schema(description = "排序")
    private Integer sort;

    /**
     * 字典描述
     */
    @Schema(description = "字典描述")
    private String description;

    /**
     * 创建人
     */
    @Schema(description = "创建人")
    private String createBy;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新人
     */
    @Schema(description = "更新人")
    private String updateBy;

    /**
     * 更新时间
     */
    @Schema(description = "更新时间")
    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;
}