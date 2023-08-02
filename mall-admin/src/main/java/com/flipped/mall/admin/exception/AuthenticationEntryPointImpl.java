package com.flipped.mall.admin.exception;

import com.flipped.mall.common.entity.api.MyResult;
import com.flipped.mall.common.util.JsonUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * 自定义认证异常处理
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2023-03-01 20:12:36
 */
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        MyResult<Void> result = MyResult.<Void>builder()
                .code(HttpStatus.UNAUTHORIZED.value())
                .message("用户认证失败")
                .success(false)
                .timestamp(LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli())
                .build();
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JsonUtil.bean2Json(result));
    }

}

