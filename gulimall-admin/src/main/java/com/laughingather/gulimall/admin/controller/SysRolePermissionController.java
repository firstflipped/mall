package com.laughingather.gulimall.admin.controller;

import com.laughingather.gulimall.admin.annotation.PlatformLogAnnotation;
import com.laughingather.gulimall.admin.entity.param.RolePermissionParam;
import com.laughingather.gulimall.admin.service.SysRolePermissionService;
import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.common.constant.LogConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 角色权限关联路由
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-20 16:44:08
 */
@RestController
@RequestMapping("/admin/role-permission")
@Api(tags = "角色权限管理模块")
public class SysRolePermissionController {

    @Resource
    private SysRolePermissionService sysRolePermissionService;

    @PostMapping
    @PreAuthorize("hasAuthority('admin:role-permission:add')")
    @ApiOperation(value = "保存角色对应权限列表")
    @PlatformLogAnnotation(type = LogConstants.INSERT, value = "保存角色对应权限列表")
    public MyResult<Void> saveRolePermissions(@Valid @RequestBody RolePermissionParam rolePermissionParam) {
        sysRolePermissionService.saveRolePermissions(rolePermissionParam);
        return MyResult.success();
    }

}
