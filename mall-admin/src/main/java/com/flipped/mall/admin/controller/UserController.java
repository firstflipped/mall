package com.flipped.mall.admin.controller;

import com.flipped.mall.admin.entity.UserEntity;
import com.flipped.mall.admin.entity.param.UserEnableParam;
import com.flipped.mall.admin.entity.param.UserPasswordParam;
import com.flipped.mall.admin.entity.query.UserQuery;
import com.flipped.mall.admin.service.UserService;
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
 * 用户管理
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@RestController
@RequestMapping("/admin/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 保存用户
     *
     * @param userEntity 用户信息
     * @return MyResult<Void>
     */
    @PostMapping
    @PreAuthorize("hasAuthority('admin:user:add')")
    @PlatformLog(type = LogConstants.INSERT, value = "保存用户")
    public MyResult<Void> saveUser(@Validated(value = AddGroup.class) @RequestBody UserEntity userEntity) {
        userService.saveUser(userEntity);
        return MyResult.success();
    }

    /**
     * 更新用户
     *
     * @param userEntity 用户信息
     * @return MyResult<Void>
     */
    @PutMapping
    @PreAuthorize("hasAuthority('admin:user:update')")
    @PlatformLog(type = LogConstants.UPDATE, value = "更新用户")
    public MyResult<Void> updateUserById(@Validated(value = UpdateGroup.class) @RequestBody UserEntity userEntity) {
        userService.updateUserById(userEntity);
        return MyResult.success();
    }

    /**
     * 更新用户状态
     *
     * @param userEnableParam 用户状态信息
     * @return MyResult<Void>
     */
    @PutMapping("/change/status")
    @PreAuthorize("hasAuthority('admin:user:update')")
    @PlatformLog(type = LogConstants.UPDATE, value = "更新用户状态")
    public MyResult<Void> updateUserStatusById(@Validated @RequestBody UserEnableParam userEnableParam) {
        userService.updateUserEnableById(userEnableParam);
        return MyResult.success();
    }

    /**
     * 更新用户密码
     *
     * @param userPasswordParam 用户密码信息
     * @return MyResult<Void>
     */
    @PutMapping("/change/password")
    @PreAuthorize("hasAuthority('admin:user:update')")
    @PlatformLog(type = LogConstants.UPDATE, value = "更新用户密码")
    public MyResult<Void> updateUserStatusById(@Validated @RequestBody UserPasswordParam userPasswordParam) {
        userService.updateUserPasswordById(userPasswordParam);
        return MyResult.success();
    }

    /**
     * 获取用户详情
     *
     * @param userid 用户id
     * @return MyResult<UserEntity> 用户详情
     */
    @GetMapping("/{uid}")
    @PreAuthorize("hasAuthority('admin:user:view')")
    @PlatformLog(value = "查询用户详情")
    public MyResult<UserEntity> getUserById(@PathVariable("uid") Long userid) {
        UserEntity user = userService.getUserById(userid);
        return MyResult.success(user);
    }

    /**
     * 查询用户列表
     *
     * @return MyResult<List < UserEntity>> 用户列表
     */
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('admin:user:view')")
    @PlatformLog(value = "查询用户列表")
    public MyResult<List<UserEntity>> listUsers() {
        List<UserEntity> users = userService.listUsers();
        return MyResult.success(users);
    }

    /**
     * 分页查询用户列表
     *
     * @param userQuery 用户列表查询条件
     * @return MyResult<MyPage < UserEntity>> 分页用户列表
     */
    @GetMapping("/page")
    @PreAuthorize("hasAuthority('admin:user:view')")
    @PlatformLog(value = "分页查询用户列表")
    public MyResult<MyPage<UserEntity>> listUserWithPage(@ModelAttribute UserQuery userQuery) {
        MyPage<UserEntity> usersWithPage = userService.listUserWithPage(userQuery);
        return MyResult.success(usersWithPage);
    }

}

