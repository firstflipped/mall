package com.laughingather.gulimall.admin.service;

import com.laughingather.gulimall.admin.entity.SysPermissionEntity;
import com.laughingather.gulimall.admin.entity.vo.PermissionsWithTreeVO;
import com.laughingather.gulimall.common.api.MyPage;

import java.util.List;

/**
 * 权限逻辑接口
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
public interface SysPermissionService {

    /**
     * 保存权限
     *
     * @param sysPermissionEntity
     */
    void savePermission(SysPermissionEntity sysPermissionEntity);

    /**
     * 批量删除权限
     *
     * @param permissionIds
     */
    void batchDeletePermission(List<Long> permissionIds);

    /**
     * 删除权限
     *
     * @param permissionId
     */
    void deletePermission(Long permissionId);

    /**
     * 更新权限
     *
     * @param sysPermissionEntity
     */
    void updatePermission(SysPermissionEntity sysPermissionEntity);

    /**
     * 权限列表
     *
     * @return
     */
    List<SysPermissionEntity> listPermissions();

    /**
     * 分页查询权限列表
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    MyPage<SysPermissionEntity> listPermissionsWithPage(Integer pageNum, Integer pageSize);

    /**
     * 树形展示权限列表
     *
     * @return
     */
    List<PermissionsWithTreeVO> listPermissionsWithTree();
}
