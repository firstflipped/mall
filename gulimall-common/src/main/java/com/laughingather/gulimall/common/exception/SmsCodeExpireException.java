package com.laughingather.gulimall.common.exception;

import com.laughingather.gulimall.common.entity.api.ErrorCodeEnum;

/**
 * 验证码过期异常
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
public class SmsCodeExpireException extends BaseException {
    public SmsCodeExpireException() {
        super(ErrorCodeEnum.SMS_CODE_EXPIRE_EXCEPTION, "验证码已过期");
    }
}