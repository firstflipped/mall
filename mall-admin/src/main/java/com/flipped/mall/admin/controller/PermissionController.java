package com.flipped.mall.admin.controller;

import com.flipped.mall.admin.entity.PermissionEntity;
import com.flipped.mall.admin.entity.param.PermissionEnableParam;
import com.flipped.mall.admin.entity.query.PermissionQuery;
import com.flipped.mall.admin.entity.vo.PermissionsWithTreeVO;
import com.flipped.mall.admin.service.PermissionService;
import com.laughingather.gulimall.common.annotation.PlatformLogAnnotation;
import com.laughingather.gulimall.common.constant.LogConstants;
import com.laughingather.gulimall.common.entity.api.MyPage;
import com.laughingather.gulimall.common.entity.api.MyResult;
import com.laughingather.gulimall.common.valid.AddGroup;
import com.laughingather.gulimall.common.valid.UpdateGroup;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 权限路由
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@RestController
@RequestMapping("/admin/permission")
@Tag(name = "权限管理模块")
public class PermissionController {

    @Resource
    private PermissionService permissionService;

    @PostMapping
    @PreAuthorize("hasAuthority('admin:permission:add')")
    @Operation(summary = "保存权限")
    @PlatformLogAnnotation(type = LogConstants.INSERT, value = "保存权限")
    public MyResult<Void> savePermission(@Validated(AddGroup.class) @RequestBody PermissionEntity permissionEntity) {
        permissionService.savePermission(permissionEntity);
        return MyResult.success();
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('admin:permission:delete')")
    @Operation(summary = "批量删除权限")
    @PlatformLogAnnotation(type = LogConstants.DELETE, value = "批量删除权限")
    public MyResult<Void> deletePermissionByIds(@RequestBody List<Long> permissionIds) {
        permissionService.batchDeletePermission(permissionIds);
        return MyResult.success();
    }

    @DeleteMapping("/{pid}")
    @PreAuthorize("hasAuthority('admin:permission:delete')")
    @Operation(summary = "删除权限")
    @PlatformLogAnnotation(type = LogConstants.DELETE, value = "删除权限")
    public MyResult<Void> deletePermissionById(@PathVariable("pid") Long permissionId) {
        permissionService.deletePermission(permissionId);
        return MyResult.success();
    }

    @PutMapping
    @PreAuthorize("hasAuthority('admin:permission:update')")
    @Operation(summary = "更新权限")
    @PlatformLogAnnotation(type = LogConstants.UPDATE, value = "更新权限")
    public MyResult<Void> updatePermissionById(@Validated(UpdateGroup.class) @RequestBody PermissionEntity permissionEntity) {
        permissionService.updatePermission(permissionEntity);
        return MyResult.success();
    }

    @PutMapping("/enable")
    @PreAuthorize("hasAuthority('admin:permission:update')")
    @Operation(summary = "启用/关闭权限")
    @PlatformLogAnnotation(type = LogConstants.UPDATE, value = "启用/关闭权限")
    public MyResult<Void> enableOrClosePermission(@Validated @RequestBody PermissionEnableParam permissionEnableParam) {
        permissionService.enableOrClosePermission(permissionEnableParam);
        return MyResult.success();
    }

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('admin:permission:view')")
    @Operation(summary = "查询权限列表")
    @PlatformLogAnnotation(value = "查询权限列表")
    public MyResult<List<PermissionEntity>> listPermissions() {
        List<PermissionEntity> permissions = permissionService.listPermissions();
        return MyResult.success(permissions);
    }

    @GetMapping("/page")
    @PreAuthorize("hasAuthority('admin:permission:view')")
    @Operation(summary = "分页查询权限列表")
    @PlatformLogAnnotation(value = "分页查询权限列表")
    public MyResult<MyPage<PermissionEntity>> listPermissionsWithPage(@ModelAttribute PermissionQuery permissionQuery) {
        MyPage<PermissionEntity> permissionsWithPage = permissionService.listPermissionsWithPage(permissionQuery);
        return MyResult.success(permissionsWithPage);
    }

    @GetMapping("/tree")
    @PreAuthorize("hasAuthority('admin:permission:view')")
    @Operation(summary = "树形查询权限列表")
    @PlatformLogAnnotation(value = "树形查询权限列表")
    public MyResult<List<PermissionsWithTreeVO>> listPermissionsWithTree() {
        List<PermissionsWithTreeVO> permissionsWithTree = permissionService.listPermissionsWithTree();
        return MyResult.success(permissionsWithTree);
    }


}

