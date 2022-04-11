package com.laughingather.gulimall.cart.interceptor;

import com.laughingather.gulimall.cart.entity.vo.UserInfoVO;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 购物车拦截器
 * 在执行目标方法之前，判断用户的登录状态
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
public class CartInterceptor implements HandlerInterceptor {

    public static ThreadLocal<UserInfoVO> threadLocal = new ThreadLocal<>();

    /**
     * 目标方法执行之前，执行该方法
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        return true;
    }


    /**
     * 目标方法执行之后，执行该方法
     * 分配临时用户，让浏览器保存
     *
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }
}

