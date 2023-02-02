package com.laughingather.gulimall.common.exception;

import com.laughingather.gulimall.common.entity.api.ErrorCodeEnum;

/**
 * 用户不存在异常
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
public class UserNotExistException extends BaseException {
    public UserNotExistException(String additionalErrorMessage) {
        super(ErrorCodeEnum.TOKEN_VERIFICATION_EXCEPTION, additionalErrorMessage);
    }
}
