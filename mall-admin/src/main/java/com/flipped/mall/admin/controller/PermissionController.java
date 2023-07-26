package com.flipped.mall.admin.controller;

import com.flipped.mall.admin.entity.PermissionEntity;
import com.flipped.mall.admin.entity.param.PermissionEnableParam;
import com.flipped.mall.admin.entity.query.PermissionQuery;
import com.flipped.mall.admin.entity.vo.PermissionsWithTreeVO;
import com.flipped.mall.admin.service.PermissionService;
import com.flipped.mall.common.aspect.annotation.PlatformLog;
import com.flipped.mall.common.constant.LogConstants;
import com.flipped.mall.common.entity.api.MyPage;
import com.flipped.mall.common.entity.api.MyResult;
import com.flipped.mall.common.valid.AddGroup;
import com.flipped.mall.common.valid.UpdateGroup;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 权限管理
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@RestController
@RequestMapping("/admin/permission")
public class PermissionController {

    @Resource
    private PermissionService permissionService;

    /**
     * 保存权限
     *
     * @param permissionEntity 权限信息
     * @return MyResult<Void>
     */
    @PostMapping
    @PreAuthorize("hasAuthority('admin:permission:add')")
    @PlatformLog(type = LogConstants.INSERT, value = "保存权限")
    public MyResult<Void> savePermission(@Validated(AddGroup.class) @RequestBody PermissionEntity permissionEntity) {
        permissionService.savePermission(permissionEntity);
        return MyResult.success();
    }


    /**
     * 批量删除权限
     *
     * @param permissionIds 权限id列表
     * @return MyResult<Void>
     */
    @DeleteMapping
    @PreAuthorize("hasAuthority('admin:permission:delete')")
    @PlatformLog(type = LogConstants.DELETE, value = "批量删除权限")
    public MyResult<Void> deletePermissionByIds(@RequestBody List<Long> permissionIds) {
        permissionService.batchDeletePermission(permissionIds);
        return MyResult.success();
    }

    /**
     * 删除权限
     *
     * @param permissionId 权限id
     * @return MyResult<Void>
     */
    @DeleteMapping("/{pid}")
    @PreAuthorize("hasAuthority('admin:permission:delete')")
    @PlatformLog(type = LogConstants.DELETE, value = "删除权限")
    public MyResult<Void> deletePermissionById(@PathVariable("pid") Long permissionId) {
        permissionService.deletePermission(permissionId);
        return MyResult.success();
    }

    /**
     * 更新权限
     *
     * @param permissionEntity 权限信息
     * @return MyResult<Void>
     */
    @PutMapping
    @PreAuthorize("hasAuthority('admin:permission:update')")
    @PlatformLog(type = LogConstants.UPDATE, value = "更新权限")
    public MyResult<Void> updatePermissionById(@Validated(UpdateGroup.class) @RequestBody PermissionEntity permissionEntity) {
        permissionService.updatePermission(permissionEntity);
        return MyResult.success();
    }

    /**
     * 启用/关闭权限
     *
     * @param permissionEnableParam 权限状态信息
     * @return MyResult<Void>
     */
    @PutMapping("/enable")
    @PreAuthorize("hasAuthority('admin:permission:update')")
    @PlatformLog(type = LogConstants.UPDATE, value = "启用/关闭权限")
    public MyResult<Void> enableOrClosePermission(@Validated @RequestBody PermissionEnableParam permissionEnableParam) {
        permissionService.enableOrClosePermission(permissionEnableParam);
        return MyResult.success();
    }

    /**
     * 查询权限列表
     *
     * @return MyResult<List < PermissionEntity>> 权限列表
     */
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('admin:permission:view')")
    @PlatformLog(value = "查询权限列表")
    public MyResult<List<PermissionEntity>> listPermissions() {
        List<PermissionEntity> permissions = permissionService.listPermissions();
        return MyResult.success(permissions);
    }

    /**
     * 分页查询权限列表
     *
     * @param permissionQuery 权限列表查询条件
     * @return MyResult<MyPage < PermissionEntity>> 分页权限列表
     */
    @GetMapping("/page")
    @PreAuthorize("hasAuthority('admin:permission:view')")
    @PlatformLog(value = "分页查询权限列表")
    public MyResult<MyPage<PermissionEntity>> listPermissionsWithPage(@ModelAttribute PermissionQuery permissionQuery) {
        MyPage<PermissionEntity> permissionsWithPage = permissionService.listPermissionsWithPage(permissionQuery);
        return MyResult.success(permissionsWithPage);
    }

    /**
     * 树形查询权限列表
     *
     * @return MyResult<List < PermissionsWithTreeVO>> 树形权限列表
     */
    @GetMapping("/tree")
    @PreAuthorize("hasAuthority('admin:permission:view')")
    @PlatformLog(value = "树形查询权限列表")
    public MyResult<List<PermissionsWithTreeVO>> listPermissionsWithTree() {
        List<PermissionsWithTreeVO> permissionsWithTree = permissionService.listPermissionsWithTree();
        return MyResult.success(permissionsWithTree);
    }


}

