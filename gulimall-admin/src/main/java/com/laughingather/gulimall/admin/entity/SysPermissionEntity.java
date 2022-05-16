package com.laughingather.gulimall.admin.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.laughingather.gulimall.common.valid.AddGroup;
import com.laughingather.gulimall.common.valid.UpdateGroup;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import java.time.LocalDateTime;

/**
 * 权限实体
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Data
@TableName(value = "sys_permission")
public class SysPermissionEntity {

    /**
     * 主键id
     */
    @Null(groups = AddGroup.class, message = "新增时，权限id必须为空")
    @NotBlank(groups = UpdateGroup.class, message = "更新时，权限id不能为空")
    private Long permissionId;

    /**
     * 权限名称
     */
    @NotBlank(message = "权限标题不能为空")
    private String permissionName;

    /**
     * 权限值
     */
    @NotBlank(message = "权限值不能为空")
    private String permissionValue;

    /**
     * 路径
     */
    private String url;

    /**
     * 权限类型
     */
    private Integer type;

    /**
     * 父id
     */
    private Long parentId;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 状态 1已启用 0未启用
     */
    @TableField(value = "`status`")
    private Integer status;

    /**
     * 菜单排序
     */
    private Integer sortNo;

    /**
     * 描述
     */
    private String description;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 更新时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}

