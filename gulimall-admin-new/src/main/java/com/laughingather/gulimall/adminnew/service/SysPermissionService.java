package com.laughingather.gulimall.adminnew.service;

import com.laughingather.gulimall.adminnew.entity.SysPermissionEntity;
import com.laughingather.gulimall.adminnew.entity.vo.PermissionsWithTreeVO;
import com.laughingather.gulimall.common.api.MyPage;

import java.util.List;

/**
 * 权限逻辑接口
 *
 * @author：laughingather
 * @create：2021-12-01 2021/12/1
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
