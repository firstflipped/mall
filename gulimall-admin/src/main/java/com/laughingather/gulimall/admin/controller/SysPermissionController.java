package com.laughingather.gulimall.admin.controller;

import com.laughingather.gulimall.admin.entity.SysPermissionEntity;
import com.laughingather.gulimall.admin.entity.param.PermissionEnableParam;
import com.laughingather.gulimall.admin.entity.vo.PermissionsWithTreeVO;
import com.laughingather.gulimall.admin.service.SysPermissionService;
import com.laughingather.gulimall.common.annotation.PlatformLogAnnotation;
import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.common.constant.LogConstants;
import com.laughingather.gulimall.common.valid.AddGroup;
import com.laughingather.gulimall.common.valid.UpdateGroup;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
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
public class SysPermissionController {

    @Resource
    private SysPermissionService sysPermissionService;

    @PostMapping
    @PreAuthorize("hasAuthority('admin:permission:add')")
    @Operation(summary = "保存权限")
    @PlatformLogAnnotation(type = LogConstants.INSERT, value = "保存权限")
    public MyResult<Void> savePermission(@Validated(AddGroup.class) @RequestBody SysPermissionEntity sysPermissionEntity) {
        sysPermissionService.savePermission(sysPermissionEntity);
        return MyResult.success();
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('admin:permission:delete')")
    @Operation(summary = "批量删除权限")
    @PlatformLogAnnotation(type = LogConstants.DELETE, value = "批量删除权限")
    public MyResult<Void> deletePermissionByIds(@RequestBody List<Long> permissionIds) {
        sysPermissionService.batchDeletePermission(permissionIds);
        return MyResult.success();
    }

    @DeleteMapping("/{pid}")
    @PreAuthorize("hasAuthority('admin:permission:delete')")
    @Operation(summary = "删除权限")
    @PlatformLogAnnotation(type = LogConstants.DELETE, value = "删除权限")
    public MyResult<Void> deletePermissionById(@PathVariable("pid") Long permissionId) {
        sysPermissionService.deletePermission(permissionId);
        return MyResult.success();
    }

    @PutMapping
    @PreAuthorize("hasAuthority('admin:permission:update')")
    @Operation(summary = "更新权限")
    @PlatformLogAnnotation(type = LogConstants.UPDATE, value = "更新权限")
    public MyResult<Void> updatePermissionById(@Validated(UpdateGroup.class) @RequestBody SysPermissionEntity sysPermissionEntity) {
        sysPermissionService.updatePermission(sysPermissionEntity);
        return MyResult.success();
    }

    @PutMapping("/enable")
    @PreAuthorize("hasAuthority('admin:permission:update')")
    @Operation(summary = "启用/关闭权限")
    @PlatformLogAnnotation(type = LogConstants.UPDATE, value = "启用/关闭权限")
    public MyResult<Void> enableOrClosePermission(@Validated @RequestBody PermissionEnableParam permissionEnableParam) {
        sysPermissionService.enableOrClosePermission(permissionEnableParam);
        return MyResult.success();
    }

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('admin:permission:view')")
    @Operation(summary = "查询权限列表")
    @PlatformLogAnnotation(value = "查询权限列表")
    public MyResult<List<SysPermissionEntity>> listPermissions() {
        List<SysPermissionEntity> permissions = sysPermissionService.listPermissions();
        return MyResult.success(permissions);
    }

    @GetMapping("/page")
    @PreAuthorize("hasAuthority('admin:permission:view')")
    @Operation(summary = "分页查询权限列表")
    @Parameters({
            @Parameter(name = "pn", description = "当前页码", example = "1"),
            @Parameter(name = "ps", description = "每页展示条数", example = "10")
    })
    @PlatformLogAnnotation(value = "分页查询权限列表")
    public MyResult<MyPage<SysPermissionEntity>> listPermissionsWithPage(@RequestParam(value = "pn", defaultValue = "1") Integer pageNum,
                                                                         @RequestParam(value = "ps", defaultValue = "10") Integer pageSize) {
        MyPage<SysPermissionEntity> permissionsWithPage = sysPermissionService.listPermissionsWithPage(pageNum, pageSize);
        return MyResult.success(permissionsWithPage);
    }

    @GetMapping("/tree")
    @PreAuthorize("hasAuthority('admin:permission:view')")
    @Operation(summary = "树形查询权限列表")
    @PlatformLogAnnotation(value = "树形查询权限列表")
    public MyResult<List<PermissionsWithTreeVO>> listPermissionsWithTree() {
        List<PermissionsWithTreeVO> permissionsWithTree = sysPermissionService.listPermissionsWithTree();
        return MyResult.success(permissionsWithTree);
    }


}

