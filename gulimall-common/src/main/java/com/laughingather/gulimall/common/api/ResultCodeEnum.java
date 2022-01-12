package com.laughingather.gulimall.common.api;

import lombok.Getter;

/**
 * 枚举了一些常用API操作码
 *
 * @author：laughingather
 * @create：2021-05-26
 */
@Getter
public enum ResultCodeEnum {

    /**
     * 操作成功
     */
    SUCCESS(200, "操作成功"),

    /**
     * 操作失败
     */
    FAILED(500, "操作失败");

    private final Integer code;
    private final String message;

    ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
