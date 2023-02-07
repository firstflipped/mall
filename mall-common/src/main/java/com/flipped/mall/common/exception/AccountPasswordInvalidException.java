package com.flipped.mall.common.exception;

import com.flipped.mall.common.entity.api.ErrorCodeEnum;

/**
 * 帐号或密码错误异常
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2023-02-03 23:03:16
 */
public class AccountPasswordInvalidException extends PlatformException {

    public AccountPasswordInvalidException() {
        super(ErrorCodeEnum.ACCOUNT_PASSWORD_INVALID_EXCEPTION, "用户名或密码错误");
    }

}
