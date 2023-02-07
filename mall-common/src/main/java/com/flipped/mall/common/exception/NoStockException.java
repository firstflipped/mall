package com.flipped.mall.common.exception;

import com.flipped.mall.common.entity.api.ErrorCodeEnum;

/**
 * 库存为空异常
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
public class NoStockException extends PlatformException {

    public NoStockException(Long skuId) {
        super(ErrorCodeEnum.NO_STOCK_EXCEPTION, "库存不足，商品id：" + skuId);
    }

}

