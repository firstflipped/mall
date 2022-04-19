package com.laughingather.gulimall.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laughingather.gulimall.admin.entity.SysPermissionEntity;
import com.laughingather.gulimall.admin.entity.SysRolePermissionEntity;

import java.util.List;

/**
 * 角色权限关联逻辑接口
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-19 17:58:13
 */
public interface SysRolePermissionService extends IService<SysRolePermissionEntity> {

    /**
     * 查询权限列表
     *
     * @param userid 用户id
     * @return
     */
    List<SysPermissionEntity> listPermissionsByUserid(Long userid);


    /**
     * 查询权限列表
     *
     * @param roleIds 角色id集合
     * @return
     */
    List<SysPermissionEntity> listPermissionsByRoleIds(List<Long> roleIds);

}
