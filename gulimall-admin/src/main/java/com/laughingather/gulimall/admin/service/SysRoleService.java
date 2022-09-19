package com.laughingather.gulimall.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laughingather.gulimall.admin.entity.SysRoleEntity;
import com.laughingather.gulimall.common.api.MyPage;

import java.util.List;

/**
 * 角色逻辑接口
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
public interface SysRoleService extends IService<SysRoleEntity> {

    /**
     * 添加角色
     *
     * @param sysRoleEntity 角色实体
     */
    void saveRole(SysRoleEntity sysRoleEntity);

    /**
     * 批量删除角色
     *
     * @param roleIds 角色id集合
     */
    void deleteBatchRoleByIds(List<Long> roleIds);

    /**
     * 删除角色
     *
     * @param roleId 角色id
     */
    void deleteRoleById(Long roleId);

    /**
     * 更新角色
     *
     * @param sysRoleEntity 角色实体
     */
    void updateRoleById(SysRoleEntity sysRoleEntity);

    /**
     * 查询角色列表
     *
     * @return 角色列表
     */
    List<SysRoleEntity> listRoles();

    /**
     * 分页查询角色列表
     *
     * @param pageNum  页面
     * @param pageSize 每页条数
     * @return 分页角色列表
     */
    MyPage<SysRoleEntity> listRolesWithPage(Integer pageNum, Integer pageSize);
}
