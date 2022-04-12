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
 * 统一异常处理
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Slf4j
@RestControllerAdvice(basePackages = "com.laughingather.gulimall.product.controller")
public class ExceptionControllerAdvice {

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
        return MyResult.failed(ErrorCodeEnum.VERIFY_EXCEPTION, errorMap);
    }

    @ExceptionHandler(value = Throwable.class)
    public MyResult<String> handleThrowable(Throwable throwable) {
        throwable.printStackTrace();
        log.error("出现异常{}，异常类型{}", throwable.getMessage(), throwable.getClass());
        return MyResult.failed(ErrorCodeEnum.UNKNOWN_EXCEPTION, throwable.getMessage());
    }


}
