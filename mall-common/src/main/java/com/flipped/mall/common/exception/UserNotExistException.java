package com.flipped.mall.common.exception;

import com.flipped.mall.common.entity.api.ErrorCodeEnum;

/**
 * 用户不存在异常
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
public class UserNotExistException extends PlatformException {
    public UserNotExistException(String additionalErrorMessage) {
        super(ErrorCodeEnum.TOKEN_VERIFICATION_EXCEPTION, additionalErrorMessage);
    }
}
