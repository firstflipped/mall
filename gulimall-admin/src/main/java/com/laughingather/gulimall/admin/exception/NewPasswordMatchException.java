package com.laughingather.gulimall.admin.exception;

/**
 * 新密码两次输入不一致
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-06-13 21:35:01
 */
public class NewPasswordMatchException extends RuntimeException {
    public NewPasswordMatchException() {
        super("新密码两次输入不一致异常");
    }
}
