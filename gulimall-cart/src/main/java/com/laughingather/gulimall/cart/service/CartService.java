package com.laughingather.gulimall.cart.service;

import com.laughingather.gulimall.cart.entity.vo.CartItemVO;
import com.laughingather.gulimall.cart.entity.vo.CartVO;

import java.util.List;
import java.util.concurrent.ExecutionException;

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
     * @param skuId
     * @param num   数量
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    CartItemVO addToCart(Long skuId, Integer num) throws ExecutionException, InterruptedException;

    /**
     * 获取购物车项
     *
     * @param skuId
     * @return
     */
    CartItemVO getCartItem(Long skuId);

    /**
     * 获取购物车
     *
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    CartVO getCart() throws ExecutionException, InterruptedException;

    /**
     * 选中购物项
     *
     * @param skuId
     * @param check
     */
    void checkItem(Long skuId, Integer check);

    /**
     * 修改购物项数量
     *
     * @param skuId
     * @param count
     */
    void changeItemCount(Long skuId, Integer count);

    /**
     * 删除购物车项
     *
     * @param skuId
     */
    void deleteItem(Long skuId);

    /**
     * 获取当前用户的购物车项
     *
     * @return
     */
    List<CartItemVO> getCurrentUserCartItems();
}
