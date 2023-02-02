package com.laughingather.gulimall.common.exception;

import com.laughingather.gulimall.common.entity.api.ErrorCodeEnum;

/**
 * 手机号码存在异常
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
public class MobileExistException extends BaseException {
    public MobileExistException(String additionalErrorMessage) {
        super(ErrorCodeEnum.MOBILE_EXIST_EXCEPTION, additionalErrorMessage);
    }
}
