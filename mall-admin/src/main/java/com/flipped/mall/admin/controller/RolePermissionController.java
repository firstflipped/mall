package com.flipped.mall.admin.controller;

import com.flipped.mall.admin.entity.param.RolePermissionParam;
import com.flipped.mall.admin.service.RolePermissionService;
import com.flipped.mall.common.annotation.PlatformLog;
import com.flipped.mall.common.entity.api.MyResult;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 角色权限管理
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-20 16:44:08
 */
@RestController
@RequestMapping("/admin/role-permission")
public class RolePermissionController {

    @Resource
    private RolePermissionService rolePermissionService;

    /**
     * 保存角色对应权限列表
     *
     * @param rolePermissionParam 角色对应权限列表信息
     * @return MyResult<Void>
     */
    @PostMapping
    @PreAuthorize("hasAuthority('admin:role-permission:add')")
    @PlatformLog(value = "保存角色对应权限列表")
    public MyResult<Void> saveRolePermissions(@Valid @RequestBody RolePermissionParam rolePermissionParam) {
        rolePermissionService.saveRolePermissions(rolePermissionParam);
        return MyResult.success();
    }

}
