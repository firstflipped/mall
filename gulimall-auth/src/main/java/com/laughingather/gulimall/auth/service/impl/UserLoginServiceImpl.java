package com.laughingather.gulimall.auth.service.impl;

import com.laughingather.gulimall.auth.entity.to.UserLoginTO;
import com.laughingather.gulimall.auth.feign.entity.UserTO;
import com.laughingather.gulimall.auth.feign.service.AdminFeignService;
import com.laughingather.gulimall.auth.service.UserLoginService;
import com.laughingather.gulimall.common.api.MyResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author：laughingather
 * @create：2021-12-06 2021/12/6
 */
@Service
public class UserLoginServiceImpl implements UserLoginService {

    @Resource
    private AdminFeignService adminFeignService;

    @Override
    public void login(UserLoginTO userLoginTO) {
        MyResult<UserTO> loginResult = adminFeignService.login(userLoginTO);

        // 登录成功
        if (loginResult.isSuccess()) {

        }
    }
}

