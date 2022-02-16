package com.laughingather.gulimall.auth.service;


import com.laughingather.gulimall.auth.entity.to.AdminLoginTO;
import com.laughingather.gulimall.auth.entity.vo.AdminVO;

/**
 * 管理登录
 *
 * @author：laughingather
 * @create：2021-12-06 2021/12/6
 */
public interface AdminLoginService {

    /**
     * 管理员登录
     *
     * @param adminLoginTO
     * @return token
     */
    String login(AdminLoginTO adminLoginTO);

    /**
     * 获取用户信息
     *
     * @param token
     * @return
     */
    AdminVO getUserinfo(String token);
}
