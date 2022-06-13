package com.laughingather.gulimall.admin.exception;

/**
 * 邮箱存在异常
 *
 * @author laughingather
 * @version v1.0
 * @since 2022-06-13 13:54:26
 */
public class EmailExistException extends RuntimeException {
    public EmailExistException() {
        super("邮箱已存在");
    }
}

