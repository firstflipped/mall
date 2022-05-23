package com.laughingather.gulimall.auth.exception;

/**
 * 验证码过期异常
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
public class SmsCodeCheckFailException extends RuntimeException {
    public SmsCodeCheckFailException() {
        super("验证码校验失败");
    }
}