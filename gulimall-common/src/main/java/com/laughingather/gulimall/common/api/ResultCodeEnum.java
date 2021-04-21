package com.laughingather.gulimall.common.api;

import lombok.Getter;

/**
 * 枚举了一些常用API操作码
 *
 * @author WangJie
 * @date 2019/4/19
 */
@Getter
public enum ResultCodeEnum {
    SUCCESS(200, "操作成功"),
    FAILED(500, "操作失败");

    private Integer code;
    private String message;

    ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
