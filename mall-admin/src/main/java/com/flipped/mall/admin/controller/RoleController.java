package com.flipped.mall.admin.controller;

import com.flipped.mall.admin.entity.RoleEntity;
import com.flipped.mall.admin.service.RoleService;
import com.flipped.mall.common.annotation.PlatformLog;
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
 * 角色管理
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@RestController
@RequestMapping("/admin/role")
public class RoleController {

    @Resource
    private RoleService roleService;

    /**
     * 保存角色
     *
     * @param roleEntity 角色信息
     * @return MyResult<Void>
     */
    @PostMapping
    @PreAuthorize("hasAuthority('admin:role:add')")
    @PlatformLog(type = LogConstants.INSERT, value = "保存角色")
    public MyResult<Void> saveRole(@Validated(AddGroup.class) @RequestBody RoleEntity roleEntity) {
        roleService.saveRole(roleEntity);
        return MyResult.success();
    }

    /**
     * 批量删除角色
     *
     * @param roleIds 角色id列表
     * @return MyResult<Void>
     */
    @DeleteMapping
    @PreAuthorize("hasAuthority('admin:role:delete')")
    @PlatformLog(type = LogConstants.DELETE, value = "批量删除角色")
    public MyResult<Void> deleteBatchRoleByIds(@RequestBody List<Long> roleIds) {
        roleService.deleteBatchRoleByIds(roleIds);
        return MyResult.success();
    }

    /**
     * 删除角色
     *
     * @param roleId 角色id
     * @return MyResult<Void>
     */
    @DeleteMapping("/{rid}")
    @PreAuthorize("hasAuthority('admin:role:delete')")
    @PlatformLog(type = LogConstants.DELETE, value = "删除角色")
    public MyResult<Void> deleteRoleById(@PathVariable("rid") Long roleId) {
        roleService.deleteRoleById(roleId);
        return MyResult.success();
    }

    /**
     * 更新角色
     *
     * @param roleEntity 角色信息
     * @return MyResult<Void>
     */
    @PutMapping
    @PreAuthorize("hasAuthority('admin:role:update')")
    @PlatformLog(type = LogConstants.UPDATE, value = "更新角色")
    public MyResult<Void> updateRoleById(@Validated(UpdateGroup.class) @RequestBody RoleEntity roleEntity) {
        roleService.updateRoleById(roleEntity);
        return MyResult.success();
    }

    /**
     * 查询角色列表
     *
     * @return MyResult<List < RoleEntity>> 角色列表
     */
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('admin:role:view')")
    @PlatformLog(value = "查询角色列表")
    public MyResult<List<RoleEntity>> listRoles() {
        List<RoleEntity> roles = roleService.listRoles();
        return MyResult.success(roles);
    }

    /**
     * 分页查询角色列表
     *
     * @param pageNum  页数
     * @param pageSize 每页条数
     * @return MyResult<MyPage < RoleEntity>> 分页角色列表
     */
    @GetMapping("/page")
    @PreAuthorize("hasAuthority('admin:role:view')")
    @PlatformLog(value = "分页查询角色列表")
    public MyResult<MyPage<RoleEntity>> listRolesWithPage(@RequestParam(value = "pn", defaultValue = "1") Integer pageNum,
                                                          @RequestParam(value = "ps", defaultValue = "10") Integer pageSize) {
        MyPage<RoleEntity> rolesWithPage = roleService.listRolesWithPage(pageNum, pageSize);
        return MyResult.success(rolesWithPage);
    }

}

