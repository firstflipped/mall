package com.laughingather.gulimall.common.exception;

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
 * 异常统一处理切面
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public MyResult<Map<String, String>> handleValidException(MethodArgumentNotValidException e) {
        log.error("数据校验异常：{}，异常类型：{}，异常详情：{}", e.getMessage(), e.getClass(), e);

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
        log.error("请求方法异常：{}，异常类型：{}，异常详情：{}", e.getMessage(), e.getClass(), e);
        return MyResult.failed(ErrorCodeEnum.ACCESS_EXCEPTION);
    }


    @ExceptionHandler(value = UsernameExistException.class)
    public MyResult<Map<String, String>> handleUsernameExistException(UsernameExistException e) {
        log.error("用户名唯一性校验异常：{}，异常类型：{}，异常详情：{}", e.getAdditionalErrorMessage(), e.getClass(), e);
        return MyResult.failed(e.getErrorCodeEnum(), e.getAdditionalErrorMessage());
    }

    @ExceptionHandler(value = MobileExistException.class)
    public MyResult<Map<String, String>> handleMobileExistException(MobileExistException e) {
        log.error("手机号码唯一性校验异常：{}，异常类型：{}，异常详情：{}", e.getAdditionalErrorMessage(), e.getClass(), e);
        return MyResult.failed(e.getErrorCodeEnum(), e.getAdditionalErrorMessage());
    }

    @ExceptionHandler(value = EmailExistException.class)
    public MyResult<Map<String, String>> handleEmailExistException(EmailExistException e) {
        log.error("邮箱唯一性校验异常：{}，异常类型：{}，异常详情：{}", e.getAdditionalErrorMessage(), e.getClass(), e);
        return MyResult.failed(e.getErrorCodeEnum(), e.getAdditionalErrorMessage());
    }

    @ExceptionHandler(value = OldPasswordCheckException.class)
    public MyResult<Map<String, String>> handleOldPasswordCheckException(OldPasswordCheckException e) {
        log.error("原密码校验失败异常：{}，异常类型：{}，异常详情：{}", e.getMessage(), e.getClass(), e);
        return MyResult.failed(ErrorCodeEnum.OLD_PASSWORD_CHECK_EXCEPTION);
    }

    @ExceptionHandler(value = NewPasswordMatchException.class)
    public MyResult<Map<String, String>> handleNewPasswordMatchException(NewPasswordMatchException e) {
        log.error("新密码两次输入不一致校验异常：{}，异常类型：{}，异常详情：{}", e.getMessage(), e.getClass(), e);
        return MyResult.failed(ErrorCodeEnum.NEW_PASSWORD_MATCH_EXCEPTION);
    }

    @ExceptionHandler(value = RuntimeException.class)
    public MyResult<Map<String, String>> handleRuntimeException(RuntimeException e) {
        log.error("未知异常：{}，异常类型：{}，异常详情：{}", e.getMessage(), e.getClass(), e);
        return MyResult.failed(ErrorCodeEnum.UNKNOWN_EXCEPTION);
    }

    @ExceptionHandler(value = Throwable.class)
    public MyResult<String> handleThrowable(Throwable throwable) {
        log.error("系统异常：{}，异常类型：{}，异常详情：{}", throwable.getMessage(), throwable.getClass(), throwable);
        return MyResult.failed(ErrorCodeEnum.UNKNOWN_EXCEPTION, throwable.getMessage());
    }


}