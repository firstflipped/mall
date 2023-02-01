package com.laughingather.gulimall.common.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 自定义统一返回结果封装实体
 * 记得要加入默认构造器，要不然会报序列化异常错误
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MyResult<T> {

    /**
     * 返回状态码
     */
    private Integer code;

    /**
     * 返回信息
     */
    private String message;

    /**
     * 返回数据
     */
    private T data;

    /**
     * 是否成功
     */
    private Boolean success;

    /**
     * 时间戳
     */
    private Long timestamp;

    public static <T> MyResult<T> success() {
        return MyResult.<T>builder().code(ResultCodeEnum.SUCCESS.getCode())
                .message(ResultCodeEnum.SUCCESS.getMessage())
                .data(null)
                .success(Boolean.TRUE)
                .timestamp(System.currentTimeMillis())
                .build();
    }

    public static <T> MyResult<T> success(T data) {
        return MyResult.<T>builder().code(ResultCodeEnum.SUCCESS.getCode())
                .message(ResultCodeEnum.SUCCESS.getMessage())
                .data(data)
                .success(Boolean.TRUE)
                .timestamp(System.currentTimeMillis())
                .build();
    }

    public static <T> MyResult<T> failed() {
        return MyResult.<T>builder().code(ResultCodeEnum.FAILED.getCode())
                .message(ResultCodeEnum.FAILED.getMessage())
                .data(null)
                .success(Boolean.FALSE)
                .timestamp(System.currentTimeMillis())
                .build();
    }

    public static <T> MyResult<T> failed(ErrorCodeEnum errorCodeEnum) {
        return MyResult.<T>builder().code(errorCodeEnum.getCode())
                .message(errorCodeEnum.getMessage())
                .data(null)
                .success(Boolean.FALSE)
                .timestamp(System.currentTimeMillis())
                .build();
    }


    /**
     * 自定义返回异常信息
     *
     * @param errorCodeEnum 异常枚举
     * @param errorMessage  异常信息
     * @return 自定义返回结果
     */
    public static <T> MyResult<T> failed(ErrorCodeEnum errorCodeEnum, String errorMessage) {
        return MyResult.<T>builder()
                .code(errorCodeEnum.getCode())
                .message(errorCodeEnum.getMessage() + ":" + errorMessage)
                .data(null)
                .success(Boolean.FALSE)
                .timestamp(System.currentTimeMillis())
                .build();
    }
}
