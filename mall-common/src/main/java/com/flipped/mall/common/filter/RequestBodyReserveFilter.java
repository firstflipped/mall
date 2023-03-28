package com.flipped.mall.common.filter;

import org.springframework.http.HttpMethod;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 针对post请求，将HttpServletRequest包一层 保留body里的参数
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2023-03-11 19:35:16
 **/
public class RequestBodyReserveFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        ServletRequest requestWrapper = null;

        if (servletRequest instanceof HttpServletRequest) {
            HttpServletRequest req = (HttpServletRequest) servletRequest;
            // POST请求类型，才获取POST请求体
            if (HttpMethod.POST.matches(req.getMethod())) {
                requestWrapper = new BodyReaderHttpServletRequestWrapper(req);
            }
        }

        if (requestWrapper == null) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            filterChain.doFilter(requestWrapper, servletResponse);
        }
    }


}
