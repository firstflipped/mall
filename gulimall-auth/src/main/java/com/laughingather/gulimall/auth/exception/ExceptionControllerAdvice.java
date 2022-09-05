package com.laughingather.gulimall.auth.exception;

import com.laughingather.gulimall.common.api.ErrorCodeEnum;
import com.laughingather.gulimall.common.api.MyResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 统一异常处理处理
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Slf4j
@RestControllerAdvice(basePackages = "com.laughingather.gulimall.auth.controller")
public class ExceptionControllerAdvice {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public MyResult<Map<String, String>> handleValidException(MethodArgumentNotValidException e) {
        log.error("数据校验异常：{}，异常类型：{}", e.getMessage(), e.getClass());

        List<String> errorMessages = new ArrayList<>();
        BindingResult bindingResult = e.getBindingResult();
        if (bindingResult.hasErrors()) {
            bindingResult.getFieldErrors().forEach(error -> {
                // 获取到错误属性字段
                String field = error.getField();
                // 获取到错误提示
                String message = error.getDefaultMessage();
                errorMessages.add(message);
            });
        }
        return MyResult.failed(ErrorCodeEnum.PARAMS_VERIFY_EXCEPTION, String.join(",", errorMessages));
    }


    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public MyResult<Map<String, String>> handleRequestMethodException(HttpRequestMethodNotSupportedException e) {
        log.error("请求方法异常：{}，异常类型：{}", e.getMessage(), e.getClass());
        return MyResult.failed(ErrorCodeEnum.ACCESS_EXCEPTION);
    }


    @ExceptionHandler(value = Throwable.class)
    public MyResult<String> handleThrowable(Throwable throwable) {
        throwable.printStackTrace();
        log.error("系统异常：{}，异常类型：{}", throwable.getMessage(), throwable.getClass());
        return MyResult.failed(ErrorCodeEnum.UNKNOWN_EXCEPTION, throwable.getMessage());
    }

}