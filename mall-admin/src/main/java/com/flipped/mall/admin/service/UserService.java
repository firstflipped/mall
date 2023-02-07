package com.flipped.mall.admin.service;

import com.flipped.mall.admin.entity.UserEntity;
import com.flipped.mall.admin.entity.dto.AdminDTO;
import com.flipped.mall.admin.entity.dto.AdminLoginDTO;
import com.flipped.mall.admin.entity.param.UserEnableParam;
import com.flipped.mall.admin.entity.param.UserPasswordParam;
import com.flipped.mall.admin.entity.query.UserQuery;
import com.laughingather.gulimall.common.entity.api.MyPage;

import java.util.List;

/**
 * 用户逻辑接口
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
public interface UserService {

    /**
     * 添加用户
     *
     * @param userEntity 用户实体
     */
    void saveUser(UserEntity userEntity);

    /**
     * 更新用户数据
     *
     * @param userEntity 用户实体
     */
    void updateUserById(UserEntity userEntity);

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
    UserEntity getUserById(Long userId);

    /**
     * 获取用户详情
     *
     * @param username 用户名
     * @return 用户信息
     */
    UserEntity getUserByUsername(String username);

    /**
     * 查询用户集合
     *
     * @return 用户列表
     */
    List<UserEntity> listUsers();

    /**
     * 分页查询用户集合
     *
     * @param userQuery 用户查询条件
     * @return 用户列表分页信息
     */
    MyPage<UserEntity> listUserWithPage(UserQuery userQuery);

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

