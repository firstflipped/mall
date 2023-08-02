package com.flipped.mall.admin.exception;

import com.flipped.mall.common.entity.api.MyResult;
import com.flipped.mall.common.util.JsonUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * 自定义权限异常处理
 * 该配置不生效，异常由全局统一异常拦截处理器处理：com.flipped.mall.admin.exception.MallAdminExceptionControllerAdvice
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2023-03-01 20:34:09
 */
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        MyResult<Void> result = MyResult.<Void>builder()
                .code(HttpStatus.FORBIDDEN.value())
                .message("用户权限不足")
                .success(false)
                .timestamp(LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli())
                .build();
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JsonUtil.bean2Json(result));
    }

}
