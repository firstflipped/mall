package com.laughingather.gulimall.adminnew.controller;

import com.laughingather.gulimall.adminnew.annotation.PlatformLogAnnotation;
import com.laughingather.gulimall.adminnew.entity.SysUserEntity;
import com.laughingather.gulimall.adminnew.service.SysUserService;
import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.common.constant.LogConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户路由
 *
 * @author：laughingather
 * @create：2021-11-24 2021/11/24
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户管理模块")
public class SysUserController {

    @Resource
    private SysUserService sysUserService;

    @PostMapping
    @ApiOperation(value = "保存用户")
    @PlatformLogAnnotation(type = LogConstants.INSERT, value = "保存用户")
    public MyResult<Void> saveUser(@RequestBody SysUserEntity sysUserEntity) {
        sysUserService.saveUser(sysUserEntity);
        return MyResult.success();
    }


    @DeleteMapping
    @ApiOperation(value = "批量删除用户")
    public MyResult<Void> deleteBatchUserByIds(@RequestBody List<Long> userIds) {
        sysUserService.deleteBatchUserByIds(userIds);
        return MyResult.success();
    }

    @PutMapping
    @ApiOperation(value = "更新用户")
    public MyResult<Void> updateUserById(@RequestBody SysUserEntity sysUserEntity) {
        sysUserService.updateUserById(sysUserEntity);
        return MyResult.success();
    }


    @GetMapping("/{uid}")
    @ApiOperation(value = "获取用户详情")
    public MyResult<SysUserEntity> getUserById(@PathVariable("uid") Long userId) {
        SysUserEntity sysUserEntity = sysUserService.getUserById(userId);
        return MyResult.success(sysUserEntity);
    }


    @GetMapping("/list")
    @ApiOperation(value = "获取用户列表")
    @PlatformLogAnnotation(value = "获取用户列表")
    public MyResult<List<SysUserEntity>> listUsers() {
        List<SysUserEntity> users = sysUserService.listUsers();
        return MyResult.success(users);
    }


    @GetMapping("/page")
    @ApiOperation(value = "分页获取用户列表")
    public MyResult<MyPage<SysUserEntity>> listUserWithPage(@RequestParam(value = "pn", defaultValue = "1") Integer pageNum,
                                                            @RequestParam(value = "ps", defaultValue = "10") Integer pageSize) {
        MyPage<SysUserEntity> usersWithPage = sysUserService.listUserWithPage(pageNum, pageSize);
        return MyResult.success(usersWithPage);
    }


}

