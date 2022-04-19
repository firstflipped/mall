package com.laughingather.gulimall.admin.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * 权限实体
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Data
@Entity
@Table(name = "sys_permission")
@TableName(value = "sys_permission")
public class SysPermissionEntity {
    /**
     * 主键id
     */
    @Id
    private Long permissionId;

    /**
     * 菜单标题
     */
    @Column(name = "permission_name")
    private String permissionName;

    /**
     * 菜单标题
     */
    @Column(name = "permission_value")
    private String permissionValue;

    /**
     * 路径
     */
    @Column(name = "url")
    private String url;

    /**
     * 权限类型
     */
    @Column(name = "type")
    private String type;

    /**
     * 父id
     */
    @Column(name = "parent_id")
    private Long parentId;

    /**
     * 菜单图标
     */
    @Column(name = "icon")
    private String icon;

    /**
     * 状态 1已启用 0未启用
     */
    @Column(name = "status")
    @TableField(value = "`status`")
    private Integer status;

    /**
     * 菜单排序
     */
    @Column(name = "sort_no")
    private Integer sortNo;

    /**
     * 描述
     */
    @Column(name = "description")
    private String description;

    /**
     * 创建人
     */
    @Column(name = "create_by")
    private String createBy;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 更新人
     */
    @Column(name = "update_by")
    private String updateBy;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}

