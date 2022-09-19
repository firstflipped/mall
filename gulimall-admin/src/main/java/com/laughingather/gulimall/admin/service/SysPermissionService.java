package com.laughingather.gulimall.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
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
public interface SysPermissionService extends IService<SysPermissionEntity> {

    /**
     * 保存权限
     *
     * @param sysPermissionEntity 权限实体
     */
    void savePermission(SysPermissionEntity sysPermissionEntity);

    /**
     * 批量删除权限
     *
     * @param permissionIds 权限id集合
     */
    void batchDeletePermission(List<Long> permissionIds);

    /**
     * 删除权限
     *
     * @param permissionId 权限id
     */
    void deletePermission(Long permissionId);

    /**
     * 更新权限
     *
     * @param sysPermissionEntity 权限实体
     */
    void updatePermission(SysPermissionEntity sysPermissionEntity);

    /**
     * 权限列表
     *
     * @return 权限列表
     */
    List<SysPermissionEntity> listPermissions();

    /**
     * 分页查询权限列表
     *
     * @param pageNum  页码
     * @param pageSize 每页条数
     * @return 分页权限列表
     */
    MyPage<SysPermissionEntity> listPermissionsWithPage(Integer pageNum, Integer pageSize);

    /**
     * 查询树形权限列表
     *
     * @return 树形结构权限列表
     */
    List<PermissionsWithTreeVO> listPermissionsWithTree();
}
