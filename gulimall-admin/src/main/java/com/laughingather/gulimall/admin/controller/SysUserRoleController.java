package com.laughingather.gulimall.admin.controller;

import com.laughingather.gulimall.admin.annotation.PlatformLogAnnotation;
import com.laughingather.gulimall.admin.entity.SysRoleEntity;
import com.laughingather.gulimall.admin.entity.param.UserRolesParam;
import com.laughingather.gulimall.admin.service.SysUserRoleService;
import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.common.constant.LogConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * 用户角色关联路由
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-20 15:02:00
 */
@RestController
@RequestMapping("/admin/user-role")
@Api(tags = "用户角色管理模块")
public class SysUserRoleController {

    @Resource
    private SysUserRoleService sysUserRoleService;

    @PostMapping
    @PreAuthorize("hasAuthority('admin:user-role:add')")
    @ApiOperation(value = "保存用户角色信息")
    @PlatformLogAnnotation(type = LogConstants.INSERT, value = "保存用户角色信息")
    public MyResult<Void> saveUserRoles(@Valid @RequestBody UserRolesParam userRolesParam) {
        sysUserRoleService.saveUserRoles(userRolesParam);
        return MyResult.success();
    }


    @GetMapping("/{uid}/roles")
    @PreAuthorize("hasAuthority('admin:user-role:view')")
    @ApiOperation(value = "获取用户对应角色列表")
    @PlatformLogAnnotation(value = "获取用户对应角色列表")
    public MyResult<List<SysRoleEntity>> listRolesBuUserid(@PathVariable("uid") Long userid) {
        List<SysRoleEntity> roles = sysUserRoleService.listRolesByUserid(userid);
        return MyResult.success(roles);
    }


}
