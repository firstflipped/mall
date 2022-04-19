package com.laughingather.gulimall.admin.service;

import com.laughingather.gulimall.admin.entity.SysRoleEntity;

import java.util.List;

/**
 * 用户角色关联逻辑接口
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-19 17:23:29
 */
public interface SysUserRoleService {

    /**
     * 获取角色列表
     *
     * @param userid 用户id
     * @return 角色列表
     */
    List<SysRoleEntity> listRolesByUserid(Long userid);

}
