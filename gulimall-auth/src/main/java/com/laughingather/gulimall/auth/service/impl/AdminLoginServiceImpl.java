package com.laughingather.gulimall.auth.service.impl;

import com.laughingather.gulimall.auth.entity.to.AdminLoginTO;
import com.laughingather.gulimall.auth.entity.vo.AdminVO;
import com.laughingather.gulimall.auth.feign.entity.AdminInfoTO;
import com.laughingather.gulimall.auth.feign.entity.AdminTO;
import com.laughingather.gulimall.auth.feign.service.AdminFeignService;
import com.laughingather.gulimall.auth.service.AdminLoginService;
import com.laughingather.gulimall.auth.service.AuthService;
import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.common.constant.AuthConstants;
import com.laughingather.gulimall.common.entity.JwtPayLoad;
import com.laughingather.gulimall.common.util.TokenProvider;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 管理登录逻辑实现
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
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

        // 生成token
        JwtPayLoad jwtPayLoad = new JwtPayLoad(adminTO.getUserid(), adminTO.getUsername());
        return authService.generateToken(jwtPayLoad);
    }

    @Override
    public AdminVO getUserinfo(String token) {
        // 解析token
        token = token.replace(AuthConstants.TOKEN_PREFIX, "");
        JwtPayLoad jwtPayLoad = TokenProvider.getJwtPayLoad(token);
        Long userid = jwtPayLoad.getUserid();

        MyResult<AdminInfoTO> getUserinfoResult = adminFeignService.getUserinfo(userid);

        // 登录成功
        if (!getUserinfoResult.isSuccess()) {
            return null;
        }

        AdminInfoTO adminInfoTO = getUserinfoResult.getData();

        // 生成token
        AdminVO adminVO = new AdminVO();
        BeanUtils.copyProperties(adminInfoTO, adminVO);
        return adminVO;
    }
}

