package com.flipped.mall.common.exception;

import com.flipped.mall.common.entity.api.ErrorCodeEnum;

/**
 * 用户名存在异常
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
public class UsernameExistException extends PlatformException {
    public UsernameExistException(String additionalErrorMessage) {
        super(ErrorCodeEnum.USERNAME_EXIST_EXCEPTION, additionalErrorMessage);
    }
}
