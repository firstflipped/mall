package com.laughingather.gulimall.common.exception;

import com.laughingather.gulimall.common.entity.api.ErrorCodeEnum;

/**
 * 库存为空异常
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
public class NoStockException extends BaseException {

    public NoStockException(Long skuId) {
        super(ErrorCodeEnum.NO_STOCK_EXCEPTION, "库存不足，商品id：" + skuId);
    }

}

