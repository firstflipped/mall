package com.flipped.mall.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.flipped.mall.admin.entity.PermissionEntity;
import com.flipped.mall.admin.entity.RolePermissionEntity;
import com.flipped.mall.admin.entity.param.RolePermissionParam;

import java.util.List;

/**
 * 角色权限关联逻辑接口
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-19 17:58:13
 */
public interface RolePermissionService extends IService<RolePermissionEntity> {

    /**
     * 查询权限列表
     *
     * @param userid 用户id
     * @return 权限列表
     */
    List<PermissionEntity> listPermissionsByUserid(Long userid);


    /**
     * 查询权限列表
     *
     * @param roleIds 角色id集合
     * @return 权限列表
     */
    List<PermissionEntity> listPermissionsByRoleIds(List<Long> roleIds);

    /**
     * 保存角色权限关系
     *
     * @param rolePermissionParam 角色权限关联关系
     */
    void saveRolePermissions(RolePermissionParam rolePermissionParam);
}
