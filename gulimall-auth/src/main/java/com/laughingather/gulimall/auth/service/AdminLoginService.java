package com.laughingather.gulimall.auth.service;


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
     * 管理员登录
     *
     * @param adminLoginTO 管理员登录参数
     * @return token
     */
    String login(AdminLoginTO adminLoginTO);

    /**
     * 获取用户信息
     *
     * @param token token
     * @return 用户展示视图类
     */
    AdminVO getUserinfo(String token);
}
