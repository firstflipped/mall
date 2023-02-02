package com.laughingather.gulimall.common.exception;

import com.laughingather.gulimall.common.entity.api.ErrorCodeEnum;
import lombok.Getter;

/**
 * 已存在异常
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2023-02-01 22:25:17
 */
@Getter
public abstract class BaseException extends RuntimeException {

    /**
     * 异常枚举
     */
    private final ErrorCodeEnum errorCodeEnum;

    /**
     * 附加异常信息
     */
    private final String additionalErrorMessage;

    public BaseException(ErrorCodeEnum errorCodeEnum, String additionalErrorMessage) {
        super(errorCodeEnum.getMessage());
        this.errorCodeEnum = errorCodeEnum;
        this.additionalErrorMessage = additionalErrorMessage;
    }

}
