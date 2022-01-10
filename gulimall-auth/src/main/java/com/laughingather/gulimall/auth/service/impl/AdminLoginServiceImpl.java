package com.laughingather.gulimall.auth.service.impl;

import com.laughingather.gulimall.auth.entity.to.AdminLoginTO;
import com.laughingather.gulimall.auth.feign.entity.AdminTO;
import com.laughingather.gulimall.auth.feign.service.AdminFeignService;
import com.laughingather.gulimall.auth.service.AdminLoginService;
import com.laughingather.gulimall.auth.service.AuthService;
import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.common.entity.JwtPayLoad;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author：laughingather
 * @create：2021-12-06 2021/12/6
 */
@Service
public class AdminLoginServiceImpl implements AdminLoginService {

    @Resource
    private AuthService authService;
    @Resource
    private AdminFeignService adminFeignService;

    @Override
    public String login(AdminLoginTO adminLoginTO) {
        MyResult<AdminTO> adminLoginResult = adminFeignService.login(adminLoginTO);

        // 登录成功
        if (!adminLoginResult.isSuccess()) {
            return null;
        }

        AdminTO adminTO = adminLoginResult.getData();
        JwtPayLoad jwtPayLoad = new JwtPayLoad(adminTO.getUserid(), adminTO.getUsername());
        String token = authService.generateToken(jwtPayLoad);
        return token;
    }
}

