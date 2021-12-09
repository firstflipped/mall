package com.laughingather.gulimall.adminnew.service;

import com.laughingather.gulimall.adminnew.entity.SysRoleEntity;
import com.laughingather.gulimall.common.api.MyPage;

import java.util.List;

/**
 * 角色逻辑接口
 *
 * @author：laughingather
 * @create：2021-11-25 2021/11/25
 */
public interface SysRoleService {

    /**
     * 添加角色
     *
     * @param sysRoleEntity
     */
    void saveRole(SysRoleEntity sysRoleEntity);

    /**
     * 批量删除角色
     *
     * @param roleIds
     */
    void deleteBatchRoleByIds(List<Long> roleIds);

    /**
     * 删除角色
     *
     * @param roleId
     */
    void deleteRoleById(Long roleId);

    /**
     * 更新角色
     *
     * @param sysRoleEntity
     */
    void updateRoleById(SysRoleEntity sysRoleEntity);

    /**
     * 查询角色列表
     *
     * @return
     */
    List<SysRoleEntity> listRoles();

    /**
     * 分页查询角色列表
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    MyPage<SysRoleEntity> listRolesWithPage(Integer pageNum, Integer pageSize);
}
