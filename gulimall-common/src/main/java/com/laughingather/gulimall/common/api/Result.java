package com.laughingather.gulimall.common.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author WangJie
 *
 * 记得要加入默认构造器，要不然会报序列化异常错误
 *
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Result {

    private Integer code;

    private String message;

    private Object data;

    public static Result success(Object data) {
        return Result.builder().code(ResultCodeEnum.SUCCESS.getCode())
                .message(ResultCodeEnum.SUCCESS.getMessage()).data(data).build();
    }

    public static Result failed() {
        return Result.builder().code(ResultCodeEnum.FAILED.getCode())
                .message(ResultCodeEnum.FAILED.getMessage()).data(null).build();
    }

}
