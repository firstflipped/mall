package com.laughingather.gulimall.adminnew.controller;

import com.laughingather.gulimall.adminnew.entity.SysUserEntity;
import com.laughingather.gulimall.adminnew.service.SysUserService;
import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.common.api.MyResult;
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
public class SysUserController {

    @Resource
    private SysUserService sysUserService;

    @PostMapping
    public MyResult saveUser(@RequestBody SysUserEntity sysUserEntity) {
        sysUserService.saveUser(sysUserEntity);
        return MyResult.success();
    }


    @DeleteMapping
    public MyResult deleteBatchUserByIds(@RequestBody List<Long> userIds) {
        sysUserService.deleteBatchUserByIds(userIds);
        return MyResult.success();
    }

    @PutMapping
    public MyResult updateUserById(@RequestBody SysUserEntity sysUserEntity) {
        sysUserService.updateUserById(sysUserEntity);
        return MyResult.success();
    }


    @GetMapping("/{uid}")
    public MyResult<SysUserEntity> getUserById(@PathVariable("uid") Long userId) {
        SysUserEntity sysUserEntity = sysUserService.getUserById(userId);
        return MyResult.success(sysUserEntity);
    }


    @GetMapping("/list")
    public MyResult<List<SysUserEntity>> listUsers() {
        List<SysUserEntity> users = sysUserService.listUsers();
        return MyResult.success(users);
    }


    @GetMapping("/page")
    public MyResult<MyPage<SysUserEntity>> listUserWithPage(@RequestParam(value = "pn", defaultValue = "1") Integer pageNum,
                                                            @RequestParam(value = "ps", defaultValue = "10") Integer pageSize) {
        MyPage<SysUserEntity> usersWithPage = sysUserService.listUserWithPage(pageNum, pageSize);
        return MyResult.success(usersWithPage);
    }

}

