package com.laughingather.gulimall.admin.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 角色权限关联实体
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("sys_role_permission")
public class RolePermissionEntity {
    /**
     * 主键id
     */
    @TableId
    private Long id;

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 权限id
     */
    private Long permissionId;
}

