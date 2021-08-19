package com.laughingather.gulimall.cart.service.impl;

import com.laughingather.gulimall.cart.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * 购物车逻辑层实现类
 *
 * @author：laughingather
 * @create：2021-08-16 2021/8/16
 */
@Slf4j
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private RedisTemplate redisTemplate;


}

