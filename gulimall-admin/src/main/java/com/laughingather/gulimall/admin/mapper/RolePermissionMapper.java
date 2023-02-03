package com.laughingather.gulimall.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.laughingather.gulimall.admin.entity.PermissionEntity;
import com.laughingather.gulimall.admin.entity.RolePermissionEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色权限关联持久
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-19 17:59:45
 */
public interface RolePermissionMapper extends BaseMapper<RolePermissionEntity> {

    /**
     * 查询权限列表
     *
     * @param userid 用户id
     * @return 用户的权限列表
     */
    List<PermissionEntity> listPermissionsByUserid(@Param("userid") Long userid);

    /**
     * 查询权限列表
     *
     * @param roleIds 角色id集合
     * @return 角色的权限列表
     */
    List<PermissionEntity> listPermissionsByRoleIds(@Param("roleIds") List<Long> roleIds);

    /**
     * 删除角色权限关联关系
     *
     * @param roleId 角色id
     */
    void deleteByRoleId(@Param("roleId") Long roleId);
}
