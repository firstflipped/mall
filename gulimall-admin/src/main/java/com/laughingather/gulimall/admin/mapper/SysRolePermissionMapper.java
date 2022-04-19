package com.laughingather.gulimall.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.laughingather.gulimall.admin.entity.SysPermissionEntity;
import com.laughingather.gulimall.admin.entity.SysRolePermissionEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色权限关联持久
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-19 17:59:45
 */
public interface SysRolePermissionMapper extends BaseMapper<SysRolePermissionEntity> {

    /**
     * 查询权限列表
     *
     * @param userid 用户id
     * @return
     */
    List<SysPermissionEntity> listPermissionsByUserid(@Param("userid") Long userid);

    /**
     * 查询权限列表
     *
     * @param roleIds 角色id集合
     * @return
     */
    List<SysPermissionEntity> listPermissionsByRoleIds(@Param("roleIds") List<Long> roleIds);
}
