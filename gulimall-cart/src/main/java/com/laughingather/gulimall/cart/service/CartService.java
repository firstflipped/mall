package com.laughingather.gulimall.cart.service;

import com.laughingather.gulimall.cart.pojo.vo.CartItemVO;

/**
 * 购物车逻辑层接口
 *
 * @author：laughingather
 * @create：2021-08-16 2021/8/16
 */
public interface CartService {

    /**
     * 添加商品到购物车
     *
     * @param skuId skuId
     * @param num   数量
     * @return
     */
    CartItemVO addToCart(Long skuId, Integer num);
}
