package com.flipped.mall.common.exception;

import com.flipped.mall.common.entity.api.ErrorCodeEnum;
import lombok.Getter;

/**
 * 已存在异常
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2023-02-01 22:25:17
 */
@Getter
public class PlatformException extends RuntimeException {

    /**
     * 异常枚举
     */
    private ErrorCodeEnum errorCodeEnum;

    /**
     * 附加异常信息
     */
    private String additionalErrorMessage;

    /**
     * 原始一场信息
     */
    private Throwable cause;

    public PlatformException(ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum.getMessage());
        this.errorCodeEnum = errorCodeEnum;
    }

    public PlatformException(ErrorCodeEnum errorCodeEnum, String additionalErrorMessage) {
        super(errorCodeEnum.getMessage());
        this.errorCodeEnum = errorCodeEnum;
        this.additionalErrorMessage = additionalErrorMessage;
    }

    public PlatformException(ErrorCodeEnum errorCodeEnum, String additionalErrorMessage, Throwable cause) {
        super(errorCodeEnum.getMessage());
        this.errorCodeEnum = errorCodeEnum;
        this.additionalErrorMessage = additionalErrorMessage;
        this.cause = cause;
    }

}
