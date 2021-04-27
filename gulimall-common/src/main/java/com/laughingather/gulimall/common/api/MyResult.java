package com.laughingather.gulimall.common.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author WangJie
 * <p>
 * 记得要加入默认构造器，要不然会报序列化异常错误
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MyResult<T> {

    private Integer code;

    private String message;

    private T data;

    public static <T> MyResult<T> success() {
        return MyResult.<T>builder().code(ResultCodeEnum.SUCCESS.getCode())
                .message(ResultCodeEnum.SUCCESS.getMessage()).data(null).build();
    }

    public static <T> MyResult<T> success(T data) {
        return MyResult.<T>builder().code(ResultCodeEnum.SUCCESS.getCode())
                .message(ResultCodeEnum.SUCCESS.getMessage()).data(data).build();
    }

    public static <T> MyResult<T> failed() {
        return MyResult.<T>builder().code(ResultCodeEnum.FAILED.getCode())
                .message(ResultCodeEnum.FAILED.getMessage()).data(null).build();
    }

    public static <T> MyResult<T> failed(T data) {
        return MyResult.<T>builder().code(ResultCodeEnum.FAILED.getCode())
                .message(ResultCodeEnum.FAILED.getMessage()).data(data).build();
    }

}
