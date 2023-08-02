package com.flipped.mall.admin.handle;

import com.flipped.mall.common.entity.api.ErrorCodeEnum;
import com.flipped.mall.common.entity.api.MyResult;
import com.flipped.mall.common.exception.ExceptionControllerAdvice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

/**
 * 后台管理异常统一处理
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2023-03-02 11:17:02
 */
@Slf4j
@RestControllerAdvice
public class MallAdminExceptionControllerAdvice extends ExceptionControllerAdvice {

    @ExceptionHandler(AccessDeniedException.class)
    public MyResult<Map<String, String>> accessDeniedException(AccessDeniedException e) throws AccessDeniedException {
        log.error("权限异常：{}，异常类型：{}，异常详情：{}", e.getMessage(), e.getClass(), e.getStackTrace());
        return MyResult.failed(ErrorCodeEnum.ACCESS_EXCEPTION);
    }

    @ExceptionHandler(AuthenticationException.class)
    public MyResult<Map<String, String>> authenticationException(AuthenticationException e) throws AuthenticationException {
        log.error("认证异常：{}，异常类型：{}，异常详情：{}", e.getMessage(), e.getClass(), e.getStackTrace());
        return MyResult.failed(ErrorCodeEnum.AUTHENTICATION_EXCEPTION);
    }

}
