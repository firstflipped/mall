package com.laughingather.gulimall.common.exception;

import com.laughingather.gulimall.common.entity.api.ErrorCodeEnum;

/**
 * 验证码过期异常
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
public class SmsCodeCheckFailException extends PlatformException {
    public SmsCodeCheckFailException() {
        super(ErrorCodeEnum.SMS_CODE_CHECK_EXCEPTION, "验证码校验失败");
    }
}