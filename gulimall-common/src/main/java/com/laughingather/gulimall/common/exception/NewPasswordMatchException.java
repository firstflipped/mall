package com.laughingather.gulimall.common.exception;

import com.laughingather.gulimall.common.entity.api.ErrorCodeEnum;

/**
 * 新密码两次输入不一致
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-06-13 21:35:01
 */
public class NewPasswordMatchException extends BaseException {

    public NewPasswordMatchException() {
        super(ErrorCodeEnum.NEW_PASSWORD_MATCH_EXCEPTION, "新密码两次输入不一致");
    }

    public NewPasswordMatchException(String additionalErrorMessage) {
        super(ErrorCodeEnum.NEW_PASSWORD_MATCH_EXCEPTION, additionalErrorMessage);
    }
}
