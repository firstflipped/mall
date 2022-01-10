package com.laughingather.gulimall.adminnew.service;

import com.laughingather.gulimall.adminnew.entity.SysUserEntity;
import com.laughingather.gulimall.adminnew.entity.to.AdminLoginTO;
import com.laughingather.gulimall.adminnew.entity.to.AdminTO;
import com.laughingather.gulimall.common.api.MyPage;

import java.util.List;

/**
 * 用户逻辑接口
 *
 * @author：laughingather
 * @create：2021-11-24 2021/11/24
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
     * @param userIds
     */
    void deleteBatchUserByIds(List<Long> userIds);

    /**
     * 更新用户数据
     *
     * @param sysUserEntity
     */
    void updateUserById(SysUserEntity sysUserEntity);

    /**
     * 获取用户详情
     *
     * @param userId
     * @return
     */
    SysUserEntity getUserById(Long userId);

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
     * 校验用户登录
     *
     * @param adminLoginTO
     * @return
     */
    AdminTO checkLogin(AdminLoginTO adminLoginTO);
}

