package com.flipped.mall.common.exception;

import com.flipped.mall.common.entity.api.ErrorCodeEnum;

/**
 * 邮箱存在异常
 *
 * @author laughingather
 * @version v1.0
 * @since 2022-06-13 13:54:26
 */
public class EmailExistException extends PlatformException {

    public EmailExistException(String additionalErrorMessage) {
        super(ErrorCodeEnum.EMAIL_EXIST_EXCEPTION, additionalErrorMessage);
    }

}

