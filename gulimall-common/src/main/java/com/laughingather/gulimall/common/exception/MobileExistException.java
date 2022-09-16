package com.laughingather.gulimall.common.exception;

/**
 * 手机号码存在异常
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
public class MobileExistException extends RuntimeException {
    public MobileExistException() {
        super("手机号码已存在");
    }
}
