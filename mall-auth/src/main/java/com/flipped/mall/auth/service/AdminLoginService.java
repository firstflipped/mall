package com.flipped.mall.auth.service;


import com.flipped.mall.auth.entity.dto.AdminLoginByMobileDTO;
import com.flipped.mall.auth.entity.dto.AdminLoginDTO;
import com.flipped.mall.auth.entity.vo.AdminVO;
import com.flipped.mall.auth.entity.vo.TokenVO;

/**
 * 管理登录逻辑接口
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
public interface AdminLoginService {

    /**
     * 管理员用户名密码登录
     *
     * @param adminLoginDTO 管理员登录参数
     * @return token
     */
    TokenVO login(AdminLoginDTO adminLoginDTO);

    /**
     * 管理员手机号验证码登录
     *
     * @param adminLoginByMobileDTO 管理员手机号登录参数
     * @return token
     */
    TokenVO loginByMobile(AdminLoginByMobileDTO adminLoginByMobileDTO);

    /**
     * 获取用户信息
     *
     * @param token token
     * @return 用户展示视图实体
     */
    AdminVO getUserinfo(String token);

    /**
     * 退出登录功能
     */
    void logout(String token);
}
