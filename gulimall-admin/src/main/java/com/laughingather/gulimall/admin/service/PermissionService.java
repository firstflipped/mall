package com.laughingather.gulimall.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laughingather.gulimall.admin.entity.PermissionEntity;
import com.laughingather.gulimall.admin.entity.param.PermissionEnableParam;
import com.laughingather.gulimall.admin.entity.query.PermissionQuery;
import com.laughingather.gulimall.admin.entity.vo.PermissionsWithTreeVO;
import com.laughingather.gulimall.common.entity.api.MyPage;

import java.util.List;

/**
 * 权限逻辑接口
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
public interface PermissionService extends IService<PermissionEntity> {

    /**
     * 保存权限
     *
     * @param permissionEntity 权限实体
     */
    void savePermission(PermissionEntity permissionEntity);

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
     * @param permissionEntity 权限实体
     */
    void updatePermission(PermissionEntity permissionEntity);

    /**
     * 启用/关闭权限
     *
     * @param permissionEnableParam 权限启用/关闭参数
     */
    void enableOrClosePermission(PermissionEnableParam permissionEnableParam);

    /**
     * 查询权限列表
     *
     * @return 权限列表
     */
    List<PermissionEntity> listPermissions();

    /**
     * 分页查询权限列表
     *
     * @param permissionQuery 权限查询条件
     * @return 权限列表分页信息
     */
    MyPage<PermissionEntity> listPermissionsWithPage(PermissionQuery permissionQuery);

    /**
     * 查询树形权限列表
     *
     * @return 树形结构权限列表
     */
    List<PermissionsWithTreeVO> listPermissionsWithTree();
}
