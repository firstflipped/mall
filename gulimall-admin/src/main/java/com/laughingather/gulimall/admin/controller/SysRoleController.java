package com.laughingather.gulimall.admin.controller;

import com.laughingather.gulimall.admin.entity.SysRoleEntity;
import com.laughingather.gulimall.admin.service.SysRoleService;
import com.laughingather.gulimall.common.annotation.PlatformLogAnnotation;
import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.common.constant.LogConstants;
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
 * 角色路由
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@RestController
@RequestMapping("/admin/role")
@Tag(name = "角色管理模块")
public class SysRoleController {

    @Resource
    private SysRoleService sysRoleService;

    @PostMapping
    @PreAuthorize("hasAuthority('admin:role:add')")
    @Operation(summary = "保存角色")
    @PlatformLogAnnotation(type = LogConstants.INSERT, value = "保存角色")
    public MyResult<Void> saveRole(@Validated(AddGroup.class) @RequestBody SysRoleEntity sysRoleEntity) {
        sysRoleService.saveRole(sysRoleEntity);
        return MyResult.success();
    }

    @DeleteMapping
    @PreAuthorize("hasAuthority('admin:role:delete')")
    @Operation(summary = "批量删除角色")
    @PlatformLogAnnotation(type = LogConstants.DELETE, value = "批量删除角色")
    public MyResult<Void> deleteBatchRoleByIds(@RequestBody List<Long> roleIds) {
        sysRoleService.deleteBatchRoleByIds(roleIds);
        return MyResult.success();
    }

    @DeleteMapping("/{rid}")
    @PreAuthorize("hasAuthority('admin:role:delete')")
    @Operation(summary = "删除角色")
    @PlatformLogAnnotation(type = LogConstants.DELETE, value = "删除角色")
    public MyResult<Void> deleteRoleById(@PathVariable("rid") Long roleId) {
        sysRoleService.deleteRoleById(roleId);
        return MyResult.success();
    }

    @PutMapping
    @PreAuthorize("hasAuthority('admin:role:update')")
    @Operation(summary = "更新角色")
    @PlatformLogAnnotation(type = LogConstants.UPDATE, value = "更新角色")
    public MyResult<Void> updateRoleById(@Validated(UpdateGroup.class) @RequestBody SysRoleEntity sysRoleEntity) {
        sysRoleService.updateRoleById(sysRoleEntity);
        return MyResult.success();
    }

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('admin:role:view')")
    @Operation(summary = "查询角色列表")
    @PlatformLogAnnotation(value = "查询角色列表")
    public MyResult<List<SysRoleEntity>> listRoles() {
        List<SysRoleEntity> roles = sysRoleService.listRoles();
        return MyResult.success(roles);
    }

    @GetMapping("/page")
    @PreAuthorize("hasAuthority('admin:role:view')")
    @Operation(summary = "分页查询角色列表")
    @PlatformLogAnnotation(value = "分页查询角色列表")
    public MyResult<MyPage<SysRoleEntity>> listRolesWithPage(@RequestParam(value = "pn", defaultValue = "1") Integer pageNum,
                                                             @RequestParam(value = "ps", defaultValue = "10") Integer pageSize) {
        MyPage<SysRoleEntity> rolesWithPage = sysRoleService.listRolesWithPage(pageNum, pageSize);
        return MyResult.success(rolesWithPage);
    }

}

