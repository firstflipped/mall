package com.flipped.mall.admin.entity.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 后台管理员权限传输类
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2023-03-13 23:18:46
 */
@Data
public class AdminPermissionDTO {

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
    private String path;

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
    private List<AdminPermissionDTO> children;

}
