package com.laughingather.gulimall.admin.controller;

import com.laughingather.gulimall.admin.entity.UserEntity;
import com.laughingather.gulimall.admin.entity.param.UserEnableParam;
import com.laughingather.gulimall.admin.entity.param.UserPasswordParam;
import com.laughingather.gulimall.admin.entity.query.UserQuery;
import com.laughingather.gulimall.admin.service.UserService;
import com.laughingather.gulimall.common.annotation.PlatformLogAnnotation;
import com.laughingather.gulimall.common.constant.LogConstants;
import com.laughingather.gulimall.common.entity.api.MyPage;
import com.laughingather.gulimall.common.entity.api.MyResult;
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
 * 用户路由
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@RestController
@RequestMapping("/admin/user")
@Tag(name = "用户管理模块")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping
    @PreAuthorize("hasAuthority('admin:user:add')")
    @Operation(summary = "保存用户")
    @PlatformLogAnnotation(type = LogConstants.INSERT, value = "保存用户")
    public MyResult<Void> saveUser(@Validated(value = AddGroup.class) @RequestBody UserEntity userEntity) {
        userService.saveUser(userEntity);
        return MyResult.success();
    }


    @PutMapping
    @PreAuthorize("hasAuthority('admin:user:update')")
    @Operation(summary = "更新用户")
    @PlatformLogAnnotation(type = LogConstants.UPDATE, value = "更新用户")
    public MyResult<Void> updateUserById(@Validated(value = UpdateGroup.class) @RequestBody UserEntity userEntity) {
        userService.updateUserById(userEntity);
        return MyResult.success();
    }


    @PutMapping("/change/status")
    @PreAuthorize("hasAuthority('admin:user:update')")
    @Operation(summary = "更新用户状态")
    @PlatformLogAnnotation(type = LogConstants.UPDATE, value = "更新用户状态")
    public MyResult<Void> updateUserStatusById(@Validated @RequestBody UserEnableParam userEnableParam) {
        userService.updateUserEnableById(userEnableParam);
        return MyResult.success();
    }

    @PutMapping("/change/password")
    @PreAuthorize("hasAuthority('admin:user:update')")
    @Operation(summary = "更新用户密码")
    @PlatformLogAnnotation(type = LogConstants.UPDATE, value = "更新用户密码")
    public MyResult<Void> updateUserStatusById(@Validated @RequestBody UserPasswordParam userPasswordParam) {
        userService.updateUserPasswordById(userPasswordParam);
        return MyResult.success();
    }


    @GetMapping("/{uid}")
    @PreAuthorize("hasAuthority('admin:user:view')")
    @Operation(summary = "获取用户详情")
    @PlatformLogAnnotation(value = "获取用户详情")
    public MyResult<UserEntity> getUserById(@PathVariable("uid") Long userid) {
        UserEntity user = userService.getUserById(userid);
        return MyResult.success(user);
    }


    @GetMapping("/list")
    @PreAuthorize("hasAuthority('admin:user:view')")
    @Operation(summary = "获取用户列表")
    @PlatformLogAnnotation(value = "获取用户列表")
    public MyResult<List<UserEntity>> listUsers() {
        List<UserEntity> users = userService.listUsers();
        return MyResult.success(users);
    }


    @GetMapping("/page")
    @PreAuthorize("hasAuthority('admin:user:view')")
    @Operation(summary = "分页获取用户列表")
    @PlatformLogAnnotation(value = "分页获取用户列表")
    public MyResult<MyPage<UserEntity>> listUserWithPage(@ModelAttribute UserQuery userQuery) {
        MyPage<UserEntity> usersWithPage = userService.listUserWithPage(userQuery);
        return MyResult.success(usersWithPage);
    }

}

