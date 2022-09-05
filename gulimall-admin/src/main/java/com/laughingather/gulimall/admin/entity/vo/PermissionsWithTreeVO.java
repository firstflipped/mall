package com.laughingather.gulimall.admin.entity.vo;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 权限属性视图展示
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Data
public class PermissionsWithTreeVO {

    /**
     * id
     */
    private Long permissionId;

    /**
     * 权限名称
     */
    private String permissionName;

    /**
     * 权限值
     */
    private String permissionValue;

    /**
     * 前端url
     */
    private String url;

    /**
     * 父id
     */
    private Long parentId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 子集
     */
    private List<PermissionsWithTreeVO> children;

}

