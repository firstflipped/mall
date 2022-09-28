package com.laughingather.gulimall.auth.service.impl;

import com.laughingather.gulimall.auth.service.AuthService;
import com.laughingather.gulimall.common.entity.JwtPayLoad;
import com.laughingather.gulimall.common.util.TokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 认证逻辑实现
 *
 * @author：laughingather
 * @create：2022-01-06 2022/1/6
 */
@Slf4j
@Service
public class AuthServiceImpl implements AuthService {


    @Override
    public String generateToken(JwtPayLoad jwtPayLoad) {
        return TokenProvider.generateToken(jwtPayLoad);
    }

    @Override
    public JwtPayLoad parseToken(String token) {
        return TokenProvider.getJwtPayLoad(token);
    }

    @Override
    public Boolean checkToken(String token) {
        return TokenProvider.checkToken(token);
    }

    @Override
    public Long getTokenExpire(String token) {
        return TokenProvider.getTokenExpire(token);
    }


}

