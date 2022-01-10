package com.laughingather.gulimall.common.api;

import lombok.Getter;

/**
 * 自定义异常枚举类
 *
 * @author laughingather
 */

@Getter
public enum ErrorCodeEnum {
    UNKNOWN_EXCEPTION(10000, "系统未知异常"),
    VERIFY_EXCEPTION(10001, "参数校验异常"),
    REQUEST_METHOD_EXCEPTION(10002, "请求类型异常"),

    PRODUCT_UP_EXCEPTION(11000, "商品上架异常"),

    USERNAME_EXIST_EXCEPTION(15000, "用户名存在异常"),
    MOBILE_EXIST_EXCEPTION(15001, "手机号已存在异常"),
    ACCOUNT_PASSWORD_INVALID_EXCEPTION(15002, "账号或密码错误"),
    OAUTH_LOGIN_EXCEPTION(15003, "社交帐号登录异常"),

    NO_STOCK_EXCEPTION(16000, "商品库存不足"),
    PRICE_VERIFICATION_EXCEPTION(16001, "验价失败"),
    TOKEN_VERIFICATION_EXCEPTION(16002, "提交订单令牌校验失败"),
    SEND_MESSAGE_EXCEPTION(16003, "发送消息到MQ失败");


    private Integer code;
    private String message;

    ErrorCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
