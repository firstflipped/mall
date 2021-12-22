package com.laughingather.gulimall.cart.service.impl;

import com.laughingather.gulimall.cart.entity.vo.CartItemVO;
import com.laughingather.gulimall.cart.entity.vo.CartVO;
import com.laughingather.gulimall.cart.entity.vo.UserInfoVO;
import com.laughingather.gulimall.cart.feign.entity.SkuInfoTO;
import com.laughingather.gulimall.cart.feign.service.ProductFeignService;
import com.laughingather.gulimall.cart.interceptor.CartInterceptor;
import com.laughingather.gulimall.cart.service.CartService;
import com.laughingather.gulimall.common.api.MyResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

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

    @Resource
    private RedisTemplate redisTemplate;
    @Resource
    private ProductFeignService productFeignService;
    @Resource
    private ThreadPoolExecutor threadPoolExecutor;

    @Override
    public CartItemVO addToCart(Long skuId, Integer num) throws ExecutionException, InterruptedException {
        BoundHashOperations cartOps = getCartOps();

        CartItemVO cartItem = (CartItemVO) cartOps.get(skuId.toString());
        // 购物车没有该商品
        if (cartItem == null) {
            // 创建对象
            CartItemVO cartItemVO = new CartItemVO();

            CompletableFuture<Void> getSkuInfoCompletableFuture = CompletableFuture.runAsync(() -> {
                // 1、远程查询当前要添加的商品的信息
                MyResult<SkuInfoTO> skuInfoResult = productFeignService.getSkuInfoBySkuId(skuId);
                if (skuInfoResult.isSuccess()) {
                    SkuInfoTO skuInfo = skuInfoResult.getData();
                    cartItemVO.setImage(skuInfo.getSkuDefaultImg());
                    cartItemVO.setTitle(skuInfo.getSkuTitle());
                    cartItemVO.setPrice(skuInfo.getPrice());
                    cartItemVO.setTotalPrice(skuInfo.getPrice().multiply(new BigDecimal(num)));
                }

                cartItemVO.setCheck(true);
                cartItemVO.setCount(num);
                cartItemVO.setSkuId(skuId);
            }, threadPoolExecutor);

            CompletableFuture<Void> getSkuSaleAttrValuesCompletableFuture = CompletableFuture.runAsync(() -> {
                // 2、远程查询sku的组合信息
                MyResult<List<String>> skuSaleAttrValuesResult = productFeignService.getSkuSaleAttrValues(skuId);
                if (skuSaleAttrValuesResult.isSuccess()) {
                    List<String> skuSaleAttrValues = skuSaleAttrValuesResult.getData();
                    cartItemVO.setSkuAttr(skuSaleAttrValues);
                }
            }, threadPoolExecutor);

            // 等待异步编排任务全部执行完成
            CompletableFuture.allOf(getSkuInfoCompletableFuture, getSkuSaleAttrValuesCompletableFuture).get();
            cartOps.put(skuId.toString(), cartItemVO);
            return cartItemVO;
        }
        // 购物车有此商品，则修改数量即可
        else {
            cartItem.setCount(cartItem.getCount() + num);
            cartItem.setTotalPrice(cartItem.getPrice().multiply(new BigDecimal(cartItem.getCount())));

            // 修改redis
            cartOps.put(skuId.toString(), cartItem);
            return cartItem;
        }
    }

    @Override
    public CartItemVO getCartItem(Long skuId) {
        BoundHashOperations cartOps = getCartOps();
        return (CartItemVO) cartOps.get(skuId.toString());
    }

    @Override
    public CartVO getCart() throws ExecutionException, InterruptedException {
        CartVO cartVO = new CartVO();

        UserInfoVO userInfoVO = CartInterceptor.threadLocal.get();
        // 登录状态
        if (userInfoVO.getUserId() != null) {
            String loginCartKy = CART_PREFIX + userInfoVO.getUserId();

            // 如果临时购物车的数据还没有进行合并
            String noLoginCartKy = CART_PREFIX + userInfoVO.getUserKey();
            List<CartItemVO> tempCartItemVOList = getCartItemVOList(noLoginCartKy);
            if (CollectionUtils.isNotEmpty(tempCartItemVOList)) {
                for (CartItemVO tempCartItemVO : tempCartItemVOList) {
                    // 如果sku相同，则只需要加数量
                    addToCart(tempCartItemVO.getSkuId(), tempCartItemVO.getCount());
                }

                // 合并完成后，清空临时购物车
                cleanCart(noLoginCartKy);
            }

            List<CartItemVO> cartItemVOList = getCartItemVOList(loginCartKy);
            cartVO.setItems(cartItemVOList);
        }
        // 没登录
        else {
            // 获取临时购物车数据
            String noLoginCartKy = CART_PREFIX + userInfoVO.getUserKey();
            List<CartItemVO> cartItemVOList = getCartItemVOList(noLoginCartKy);
            cartVO.setItems(cartItemVOList);
        }

        // 计算购物车总价格、总件数
        calculationTotal(cartVO);

        return cartVO;
    }


    @Override
    public void checkItem(Long skuId, Integer check) {
        CartItemVO cartItem = getCartItem(skuId);
        cartItem.setCheck(check.equals(1) ? true : false);

        // 数据更新到redis
        getCartOps().put(skuId.toString(), cartItem);
    }

    @Override
    public void changeItemCount(Long skuId, Integer count) {
        CartItemVO cartItem = getCartItem(skuId);
        cartItem.setCount(count);
        cartItem.setTotalPrice(cartItem.getPrice().multiply(new BigDecimal(count)));

        // 数据更新到redis
        getCartOps().put(skuId.toString(), cartItem);
    }


    @Override
    public void deleteItem(Long skuId) {
        // 删除指定项
        getCartOps().delete(skuId.toString());
    }

    @Override
    public List<CartItemVO> getCurrentUserCartItems() {
        UserInfoVO userInfoVO = CartInterceptor.threadLocal.get();
        // 如果当前用户为空，则直接返回null
        if (userInfoVO == null) {
            return null;
        }

        // 获取购物车项
        String cartKey = CART_PREFIX + userInfoVO.getUserId();
        List<CartItemVO> cartItemVOList = getCartItemVOList(cartKey);

        // 过滤掉没选中的购物项
        List<CartItemVO> result = cartItemVOList.stream().filter(CartItemVO::getCheck).map(item -> {
            // 更新价格为当前最新价格（需要调用第三方服务）
            MyResult<BigDecimal> skuPriceBySkuId = productFeignService.getSkuPriceBySkuId(item.getSkuId());
            if (skuPriceBySkuId.isSuccess()) {
                item.setPrice(skuPriceBySkuId.getData());
            }
            return item;
        }).collect(Collectors.toList());
        return result;
    }


    /**
     * 计算购物车总价格、总数量
     *
     * @param cartVO
     */
    private void calculationTotal(CartVO cartVO) {
        // 初始化值
        cartVO.setTotalAmount(new BigDecimal(0));
        cartVO.setCountNum(0);

        List<CartItemVO> items = cartVO.getItems();
        for (CartItemVO item : items) {
            if (item.getCheck()) {
                cartVO.setCountNum(cartVO.getCountNum() + item.getCount());
                cartVO.setTotalAmount(cartVO.getTotalAmount().add(item.getTotalPrice()));
            }
        }
    }


    /**
     * 清空购物车
     */
    public void cleanCart(String cartKey) {
        redisTemplate.delete(cartKey);
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


    /**
     * 获取购物车项列表
     *
     * @param cartKey 购物车键
     * @return
     */
    private List<CartItemVO> getCartItemVOList(String cartKey) {
        BoundHashOperations hashOps = redisTemplate.boundHashOps(cartKey);
        return hashOps.values();
    }

}

