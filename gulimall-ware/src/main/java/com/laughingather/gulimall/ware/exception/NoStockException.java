package com.laughingather.gulimall.ware.exception;

/**
 * 库存为空异常
 *
 * @author：laughingather
 * @create：2021-10-20 2021/10/20
 */
public class NoStockException extends RuntimeException {

    public NoStockException(Long skuId) {
        super("库存不足，商品id：" + skuId);
    }

}

