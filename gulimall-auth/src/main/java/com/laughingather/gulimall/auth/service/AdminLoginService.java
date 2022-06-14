package com.laughingather.gulimall.auth.service;


import com.laughingather.gulimall.auth.entity.to.AdminLoginByMobileTO;
import com.laughingather.gulimall.auth.entity.to.AdminLoginTO;
import com.laughingather.gulimall.auth.entity.vo.AdminVO;

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
     * @param adminLoginTO 管理员登录参数
     * @return token
     */
    String login(AdminLoginTO adminLoginTO);

    /**
     * 管理员手机号验证码登录
     *
     * @param adminLoginByMobileTO 管理员手机号登录参数
     * @return token
     */
    String loginByMobile(AdminLoginByMobileTO adminLoginByMobileTO);

    /**
     * 获取用户信息
     *
     * @param token token
     * @return 用户展示视图实体
     */
    AdminVO getUserinfo(String token);


}
