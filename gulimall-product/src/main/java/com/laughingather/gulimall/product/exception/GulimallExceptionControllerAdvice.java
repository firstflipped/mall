package com.laughingather.gulimall.product.exception;

import com.laughingather.gulimall.common.api.ErrorCodeEnum;
import com.laughingather.gulimall.common.api.MyResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * @author WangJie
 */
@Slf4j
@RestControllerAdvice(basePackages = "com.laughingather.gulimall.product.controller")
public class GulimallExceptionControllerAdvice {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public MyResult<Map<String, String>> handleValidException(MethodArgumentNotValidException e) {
        log.error("数据校验出现问题{}，异常类型{}", e.getMessage(), e.getClass());
        Map<String, String> errorMap = new HashMap<>(8);

        BindingResult bindingResult = e.getBindingResult();
        if (bindingResult.hasErrors()) {
            bindingResult.getFieldErrors().forEach(error -> {
                // 获取到错误属性字段
                String field = error.getField();
                // 获取到错误提示
                String message = error.getDefaultMessage();
                errorMap.put(field, message);
            });
        }
        return MyResult.<Map<String, String>>builder().code(ErrorCodeEnum.VERIFY_EXCEPTION.getCode())
                .message(ErrorCodeEnum.VERIFY_EXCEPTION.getMessage())
                .data(errorMap)
                .build();
    }

    @ExceptionHandler(value = Throwable.class)
    public MyResult handleThrowable(Throwable throwable) {
        log.error("出现异常{}，异常类型{}", throwable.getMessage(), throwable.getClass());
        return MyResult.builder().code(ErrorCodeEnum.UNKNOWN_EXCEPTION.getCode())
                .message(ErrorCodeEnum.UNKNOWN_EXCEPTION.getMessage())
                .data(throwable.getMessage())
                .build();
    }


}
