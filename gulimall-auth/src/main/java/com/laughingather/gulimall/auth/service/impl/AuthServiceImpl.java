package com.laughingather.gulimall.auth.service.impl;

import com.laughingather.gulimall.auth.service.AuthService;
import com.laughingather.gulimall.auth.util.TokenProvider;
import com.laughingather.gulimall.common.entity.JwtPayLoad;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 认证逻辑实现
 *
 * @author：laughingather
 * @create：2022-01-06 2022/1/6
 */
@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public String generateToken(JwtPayLoad jwtPayLoad) {
        String token = TokenProvider.generateToken(jwtPayLoad);

        return token;
    }

    @Override
    public String getTokenFromRequest(HttpServletRequest request) {
        return null;
    }

    @Override
    public void checkToken(String token) {

    }


}

