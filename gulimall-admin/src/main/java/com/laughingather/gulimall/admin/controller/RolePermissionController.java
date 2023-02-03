package com.laughingather.gulimall.admin.controller;

import com.laughingather.gulimall.admin.entity.param.RolePermissionParam;
import com.laughingather.gulimall.admin.service.RolePermissionService;
import com.laughingather.gulimall.common.annotation.PlatformLogAnnotation;
import com.laughingather.gulimall.common.entity.api.MyResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "角色权限管理模块")
public class RolePermissionController {

    @Resource
    private RolePermissionService rolePermissionService;

    @PostMapping
    @PreAuthorize("hasAuthority('admin:role-permission:add')")
    @Operation(summary = "保存角色对应权限列表")
    @PlatformLogAnnotation(value = "保存角色对应权限列表")
    public MyResult<Void> saveRolePermissions(@Valid @RequestBody RolePermissionParam rolePermissionParam) {
        rolePermissionService.saveRolePermissions(rolePermissionParam);
        return MyResult.success();
    }

}
