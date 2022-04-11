package com.laughingather.gulimall.admin.controller;

import com.laughingather.gulimall.admin.entity.SysRoleEntity;
import com.laughingather.gulimall.admin.service.SysRoleService;
import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.common.api.MyResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "角色管理模块")
public class SysRoleController {

    @Resource
    private SysRoleService sysRoleService;

    @PostMapping
    @ApiOperation(value = "保存角色")
    public MyResult<Void> saveRole(@RequestBody SysRoleEntity sysRoleEntity) {
        sysRoleService.saveRole(sysRoleEntity);
        return MyResult.success();
    }

    @DeleteMapping
    @ApiOperation(value = "批量删除角色")
    public MyResult<Void> deleteBatchRoleByIds(@RequestBody List<Long> roleIds) {
        sysRoleService.deleteBatchRoleByIds(roleIds);
        return MyResult.success();
    }

    @DeleteMapping("/{rid}")
    @ApiOperation("删除角色")
    public MyResult<Void> deleteRoleById(@PathVariable("rid") Long roleId) {
        sysRoleService.deleteRoleById(roleId);
        return MyResult.success();
    }

    @PutMapping
    @ApiOperation(value = "更新角色")
    public MyResult<Void> updateRoleById(@RequestBody SysRoleEntity sysRoleEntity) {
        sysRoleService.updateRoleById(sysRoleEntity);
        return MyResult.success();
    }

    @GetMapping("/list")
    @ApiOperation(value = "查询角色列表")
    public MyResult<List<SysRoleEntity>> listRoles() {
        List<SysRoleEntity> roles = sysRoleService.listRoles();
        return MyResult.success(roles);
    }

    @GetMapping("/page")
    @ApiOperation("分页查询角色列表")
    public MyResult<MyPage<SysRoleEntity>> listRolesWithPage(@RequestParam(value = "pn", defaultValue = "1") Integer pageNum,
                                                             @RequestParam(value = "ps", defaultValue = "10") Integer pageSize) {
        MyPage<SysRoleEntity> rolesWithPage = sysRoleService.listRolesWithPage(pageNum, pageSize);
        return MyResult.success(rolesWithPage);
    }


}

