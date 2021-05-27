package com.laughingather.gulimall.common.api;

import lombok.Getter;

@Getter
public enum ErrorCodeEnum {
    UNKNOW_EXCEPTION(10000, "系统未知异常"),
    VAILD_EXCEPTION(10001, "参数校验异常"),
    REQUEST_METHOD_EXCEPTION(10002, "请求类型异常"),

    PRODUCT_UP_EXCEPTION(11000, "商品上架异常"),

    USERNAME_EXIST_EXCEPTION(15000, "用户名存在异常"),
    MOBILE_EXIST_EXCEPTION(15001, "手机号已存在异常"),
    ACCOUNT_PASSWORD_INVAILD_EXCEPTION(15002, "账号或密码错误");


    private Integer code;
    private String message;

    ErrorCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
