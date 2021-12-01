package com.laughingather.gulimall.adminnew.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "sys_permission")
public class SysPermissionEntity {
    /**
     * 主键id
     */
    @Id
    private Long id;

    /**
     * 父id
     */
    @Column(name = "parent_id")
    private Long parentId;

    /**
     * 菜单标题
     */
    @Column(name = "name")
    private String name;

    /**
     * 路径
     */
    @Column(name = "url")
    private String url;

    /**
     * 组件
     */
    @Column(name = "component")
    private String component;

    /**
     * 组件名字
     */
    @Column(name = "component_name")
    private String componentName;

    /**
     * 一级菜单跳转地址
     */
    @Column(name = "redirect")
    private String redirect;

    /**
     * 菜单类型(0:一级菜单; 1:子菜单:2:按钮权限)
     */
    @Column(name = "menu_type")
    private Integer menuType;

    /**
     * 菜单权限编码
     */
    @Column(name = "perms")
    private String perms;

    /**
     * 权限策略1显示2禁用
     */
    @Column(name = "perms_type")
    private Integer permsType;

    /**
     * 菜单排序
     */
    @Column(name = "sort_no")
    private Integer sortNo;

    /**
     * 聚合子路由: 1是0否
     */
    @Column(name = "always_show")
    private Integer alwaysShow;

    /**
     * 菜单图标
     */
    @Column(name = "icon")
    private String icon;

    /**
     * 是否路由菜单: 0:不是  1:是（默认值1）
     */
    @Column(name = "is_route")
    private Integer route;

    /**
     * 是否叶子节点:    1:是   0:不是
     */
    @Column(name = "is_leaf")
    private Integer leaf;

    /**
     * 是否缓存该页面:    1:是   0:不是
     */
    @Column(name = "keep_alive")
    private Integer keepAlive;

    /**
     * 是否隐藏路由: 0否,1是
     */
    @Column(name = "hidden")
    private Integer hidden;

    /**
     * 是否隐藏tab: 0否,1是
     */
    @Column(name = "hide_tab")
    private Integer hideTab;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private LocalDateTime updateTime;

    /**
     * 删除状态 0正常 1已删除
     */
    @Column(name = "is_delete")
    private Integer delete;

    /**
     * 是否添加数据权限1是0否
     */
    @Column(name = "is_rule")
    private Integer rule;

    /**
     * 按钮权限状态(0无效1有效)
     */
    @Column(name = "status")
    private Integer status;

    /**
     * 外链菜单打开方式 0/内部打开 1/外部打开
     */
    @Column(name = "internal_or_external")
    private Integer internalOrExternal;
}

