package com.laughingather.gulimall.common.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
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
    private Long timestamp = System.currentTimeMillis();

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

    public static <T> MyResult<T> failed(ErrorCodeEnum errorCodeEnum) {
        return MyResult.<T>builder().code(errorCodeEnum.getCode())
                .message(errorCodeEnum.getMessage()).data(null).build();
    }

    public static <T> MyResult<T> failed(ErrorCodeEnum errorCodeEnum, String errorMessage) {
        return MyResult.<T>builder().code(errorCodeEnum.getCode())
                .message(errorCodeEnum.getMessage() + ":" + errorMessage).build();
    }

    public static <T> MyResult<T> errMsg(String msg) {
        return MyResult.<T>builder().code(ResultCodeEnum.FAILED.getCode())
                .message(msg).data(null).build();
    }

    /**
     * 是否成功
     *
     * @return True Or False
     */
    public Boolean isSuccess() {
        return Objects.equals(ResultCodeEnum.SUCCESS.getCode(), code);
    }


}
