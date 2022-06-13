package com.laughingather.gulimall.admin.exception;

/**
 * 密码校验异常
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-06-13 21:20:00
 */
public class OldPasswordCheckException extends RuntimeException {
    public OldPasswordCheckException() {
        super("原密码校验失败");
    }
}
