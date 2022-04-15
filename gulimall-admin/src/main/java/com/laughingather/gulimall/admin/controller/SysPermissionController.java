package com.laughingather.gulimall.admin.controller;

import com.laughingather.gulimall.admin.annotation.PlatformLogAnnotation;
import com.laughingather.gulimall.admin.entity.SysPermissionEntity;
import com.laughingather.gulimall.admin.entity.vo.PermissionsWithTreeVO;
import com.laughingather.gulimall.admin.service.SysPermissionService;
import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.common.constant.LogConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "权限管理模块")
public class SysPermissionController {

    @Resource
    private SysPermissionService sysPermissionService;

    @PostMapping
    @ApiOperation(value = "保存权限")
    @PlatformLogAnnotation(type = LogConstants.INSERT, value = "保存权限")
    public MyResult<Void> savePermission(@RequestBody SysPermissionEntity sysPermissionEntity) {
        sysPermissionService.savePermission(sysPermissionEntity);
        return MyResult.success();
    }

    @DeleteMapping
    @ApiOperation(value = "批量删除权限")
    @PlatformLogAnnotation(type = LogConstants.DELETE, value = "批量删除权限")
    public MyResult<Void> deletePermissionByIds(@RequestBody List<Long> permissionIds) {
        sysPermissionService.batchDeletePermission(permissionIds);
        return MyResult.success();
    }

    @DeleteMapping("/{pid}")
    @ApiOperation(value = "删除权限")
    @PlatformLogAnnotation(type = LogConstants.DELETE, value = "删除权限")
    public MyResult<Void> deletePermissionById(@PathVariable("pid") Long permissionId) {
        sysPermissionService.deletePermission(permissionId);
        return MyResult.success();
    }

    @PutMapping
    @ApiOperation(value = "更新权限")
    @PlatformLogAnnotation(type = LogConstants.UPDATE, value = "更新权限")
    public MyResult<Void> updatePermissionById(@RequestBody SysPermissionEntity sysPermissionEntity) {
        sysPermissionService.updatePermission(sysPermissionEntity);
        return MyResult.success();
    }

    @GetMapping("/list")
    @ApiOperation("查询权限列表")
    @PlatformLogAnnotation(value = "查询权限列表")
    public MyResult<List<SysPermissionEntity>> listPermissions() {
        List<SysPermissionEntity> permissions = sysPermissionService.listPermissions();
        return MyResult.success(permissions);
    }

    @GetMapping("/page")
    @ApiOperation(value = "分页查询权限列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pn", value = "当前页码", defaultValue = "1", dataTypeClass = Integer.class, example = "1"),
            @ApiImplicitParam(name = "ps", value = "每页展示条数", defaultValue = "10", dataTypeClass = Integer.class, example = "10")
    })
    @PlatformLogAnnotation(value = "分页查询权限列表")
    public MyResult<MyPage<SysPermissionEntity>> listPermissionsWithPage(@RequestParam(value = "pn", defaultValue = "1") Integer pageNum,
                                                                         @RequestParam(value = "ps", defaultValue = "10") Integer pageSize) {
        MyPage<SysPermissionEntity> permissionsWithPage = sysPermissionService.listPermissionsWithPage(pageNum, pageSize);
        return MyResult.success(permissionsWithPage);
    }


    @GetMapping("/tree")
    @ApiOperation("树形查询权限列表")
    @PlatformLogAnnotation(value = "树形查询权限列表")
    public MyResult<List<PermissionsWithTreeVO>> listPermissionsWithTree() {
        List<PermissionsWithTreeVO> permissionsWithTree = sysPermissionService.listPermissionsWithTree();
        return MyResult.success(permissionsWithTree);
    }


}

