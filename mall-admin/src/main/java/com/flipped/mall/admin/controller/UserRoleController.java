package com.flipped.mall.admin.controller;

import com.flipped.mall.admin.entity.RoleEntity;
import com.flipped.mall.admin.entity.param.UserRolesParam;
import com.flipped.mall.admin.service.UserRoleService;
import com.flipped.mall.common.annotation.PlatformLog;
import com.flipped.mall.common.constant.LogConstants;
import com.flipped.mall.common.entity.api.MyResult;
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
public class UserRoleController {

    @Resource
    private UserRoleService userRoleService;

    @PostMapping
    @PreAuthorize("hasAuthority('admin:user-role:add')")
    @PlatformLog(type = LogConstants.INSERT, value = "保存用户角色信息")
    public MyResult<Void> saveUserRoles(@Valid @RequestBody UserRolesParam userRolesParam) {
        userRoleService.saveUserRoles(userRolesParam);
        return MyResult.success();
    }


    @GetMapping("/{uid}/roles")
    @PreAuthorize("hasAuthority('admin:user-role:view')")
    @PlatformLog(value = "获取用户对应角色列表")
    public MyResult<List<RoleEntity>> listRolesBuUserid(@PathVariable("uid") Long userid) {
        List<RoleEntity> roles = userRoleService.listRolesByUserid(userid);
        return MyResult.success(roles);
    }

}
