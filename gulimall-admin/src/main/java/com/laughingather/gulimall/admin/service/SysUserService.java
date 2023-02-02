package com.laughingather.gulimall.admin.service;

import com.laughingather.gulimall.admin.entity.SysUserEntity;
import com.laughingather.gulimall.admin.entity.dto.AdminDTO;
import com.laughingather.gulimall.admin.entity.dto.AdminLoginDTO;
import com.laughingather.gulimall.admin.entity.param.UserEnableParam;
import com.laughingather.gulimall.admin.entity.param.UserPasswordParam;
import com.laughingather.gulimall.common.entity.api.MyPage;

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
     * @param sysUserEntity 用户实体
     */
    void saveUser(SysUserEntity sysUserEntity);

    /**
     * 更新用户数据
     *
     * @param sysUserEntity 用户实体
     */
    void updateUserById(SysUserEntity sysUserEntity);

    /**
     * 更新用户状态
     *
     * @param userEnableParam 用户状态实体
     */
    void updateUserEnableById(UserEnableParam userEnableParam);

    /**
     * 更新用户密码
     *
     * @param userPasswordParam 用户密码实体
     */
    void updateUserPasswordById(UserPasswordParam userPasswordParam);

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
     * @param username 用户名
     * @return 用户信息
     */
    SysUserEntity getUserByUsername(String username);

    /**
     * 查询用户集合
     *
     * @return 用户列表
     */
    List<SysUserEntity> listUsers();

    /**
     * 分页查询用户集合
     *
     * @param pageNum  页码
     * @param pageSize 每页显示条数
     * @return 用户列表
     */
    MyPage<SysUserEntity> listUserWithPage(Integer pageNum, Integer pageSize);

    /**
     * 用户名密码登录
     *
     * @param adminLoginDTO 用户名密码传输类
     * @return 用户信息
     */
    AdminDTO login(AdminLoginDTO adminLoginDTO);

    /**
     * 手机号验证码登录
     *
     * @param mobile 手机号
     * @return 用户信息
     */
    AdminDTO loginByMobile(String mobile);
}

