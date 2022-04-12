package com.laughingather.gulimall.ware.exception;

/**
 * 库存为空异常
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
public class NoStockException extends RuntimeException {

    public NoStockException(Long skuId) {
        super("库存不足，商品id：" + skuId);
    }

}

