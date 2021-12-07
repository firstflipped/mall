package com.laughingather.gulimall.auth.service.impl;

import com.laughingather.gulimall.auth.entity.dto.UserLoginDTO;
import com.laughingather.gulimall.auth.feign.entity.UserDTO;
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
    public void login(UserLoginDTO userLoginDTO) {
        MyResult<UserDTO> loginResult = adminFeignService.login(userLoginDTO);

        // 登录成功
        if (loginResult.isSuccess()) {

        }
    }
}

