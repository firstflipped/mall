package com.laughingather.gulimall.common.exception;

import com.laughingather.gulimall.common.api.ErrorCodeEnum;

/**
 * 用户名存在异常
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
public class UsernameExistException extends BaseException {
    public UsernameExistException(String additionalErrorMessage) {
        super(ErrorCodeEnum.USERNAME_EXIST_EXCEPTION, additionalErrorMessage);
    }
}
