package com.laughingather.gulimall.cart.service.impl;

import com.laughingather.gulimall.cart.feign.entity.SkuInfoEntity;
import com.laughingather.gulimall.cart.feign.service.ProductFeignService;
import com.laughingather.gulimall.cart.interceptor.CartInterceptor;
import com.laughingather.gulimall.cart.pojo.vo.CartItemVO;
import com.laughingather.gulimall.cart.pojo.vo.UserInfoVO;
import com.laughingather.gulimall.cart.service.CartService;
import com.laughingather.gulimall.common.api.MyResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * 购物车逻辑层实现类
 *
 * @author：laughingather
 * @create：2021-08-16 2021/8/16
 */
@Slf4j
@Service
public class CartServiceImpl implements CartService {

    public static final String CART_PREFIX = "gulimall:cart:";

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private ProductFeignService productFeignService;

    @Override
    public CartItemVO addToCart(Long skuId, Integer num) {
        BoundHashOperations cartOps = getCartOps();

        // 远程查询当前要添加的商品的信息
        MyResult<SkuInfoEntity> result = productFeignService.getSkuInfoBySkuId(skuId);
        SkuInfoEntity skuInfo = result.getData();

        CartItemVO cartItemVO = new CartItemVO();
        cartItemVO.setCheck(true);
        cartItemVO.setCount(num);
        cartItemVO.setImage(skuInfo.getSkuDefaultImg());
        cartItemVO.setSkuId(skuId);
        cartItemVO.setTitle(skuInfo.getSkuTitle());
        cartItemVO.setPrice(skuInfo.getPrice());
        cartItemVO.setTotalPrice(skuInfo.getPrice().multiply(new BigDecimal(num)));


        return null;
    }


    /**
     * 获取到我们要操作的购物车
     *
     * @return
     */
    private BoundHashOperations getCartOps() {
        UserInfoVO userInfoVO = CartInterceptor.threadLocal.get();
        // 1、
        String cartKey = "";
        if (userInfoVO.getUserId() != null) {
            cartKey = CART_PREFIX + userInfoVO.getUserId();
        } else {
            cartKey = CART_PREFIX + userInfoVO.getUserKey();
        }

        BoundHashOperations hashOps = redisTemplate.boundHashOps(cartKey);
        return hashOps;
    }
}

