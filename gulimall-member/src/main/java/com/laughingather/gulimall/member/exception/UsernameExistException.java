package com.laughingather.gulimall.member.exception;

/**
 * 用户名存在异常
 *
 * @author：laughingather
 * @create：2021-05-26 23:05
 */
public class UsernameExistException extends RuntimeException {
    public UsernameExistException() {
        super("用户名存在");
    }
}
