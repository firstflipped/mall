package com.laughingather.gulimall.auth.service;

import com.laughingather.gulimall.auth.entity.to.UserLoginTO;

/**
 * 管理登录
 *
 * @author：laughingather
 * @create：2021-12-06 2021/12/6
 */
public interface UserLoginService {

    /**
     * 管理员登录
     *
     * @param userLoginTO
     */
    void login(UserLoginTO userLoginTO);
}
