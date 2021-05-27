package com.laughingather.gulimall.member.exception;

/**
 * 手机号码存在异常
 *
 * @author：laughingather
 * @create：2021-05-26 23:05
 */
public class MobileExistException extends RuntimeException {
    public MobileExistException() {
        super("手机号存在");
    }
}
