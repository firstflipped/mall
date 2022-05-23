package com.laughingather.gulimall.admin.service;

import com.laughingather.gulimall.admin.entity.SysUserEntity;
import com.laughingather.gulimall.admin.entity.to.AdminLoginTO;
import com.laughingather.gulimall.admin.entity.to.AdminTO;
import com.laughingather.gulimall.common.api.MyPage;

import java.util.List;

/**
 * 用户逻辑接口
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
public interface SysUserService {

    /**
     * 添加用户
     *
     * @param sysUser
     */
    void saveUser(SysUserEntity sysUser);

    /**
     * 批量删除用户
     *
     * @param useridList
     */
    void deleteBatchUserByIds(List<Long> useridList);

    /**
     * 更新用户数据
     *
     * @param sysUserEntity
     */
    void updateUserById(SysUserEntity sysUserEntity);

    /**
     * 获取用户详情
     *
     * @param userId 用户id
     * @return 用户信息
     */
    SysUserEntity getUserById(Long userId);

    /**
     * 获取用户详情
     *
     * @param username
     * @return 用户信息
     */
    SysUserEntity getUserByUsername(String username);

    /**
     * 查询用户集合
     *
     * @return
     */
    List<SysUserEntity> listUsers();

    /**
     * 分页查询用户集合
     *
     * @param pageNum  页码
     * @param pageSize 每页显示条数
     * @return
     */
    MyPage<SysUserEntity> listUserWithPage(Integer pageNum, Integer pageSize);

    /**
     * 用户名密码登录
     *
     * @param adminLoginTO 用户名密码传输类
     * @return 用户信息
     */
    AdminTO login(AdminLoginTO adminLoginTO);

    /**
     * 手机号验证码登录
     *
     * @param mobile 手机号
     * @return 用户信息
     */
    AdminTO loginByMobile(String mobile);
}

