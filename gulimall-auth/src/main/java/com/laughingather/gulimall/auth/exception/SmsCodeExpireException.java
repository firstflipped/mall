package com.laughingather.gulimall.auth.exception;

/**
 * 验证码过期异常
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
public class SmsCodeExpireException extends RuntimeException {
    public SmsCodeExpireException() {
        super("验证码已过期");
    }
}