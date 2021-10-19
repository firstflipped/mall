package com.laughingather.gulimall.order.interceptor;

import com.laughingather.gulimall.common.constant.AuthConstants;
import com.laughingather.gulimall.common.entity.MemberEntity;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 购物车拦截器
 * 在执行目标方法之前，判断用户的登录状态
 *
 * @author：laughingather
 * @create：2021-08-16 2021/8/16
 */
public class LoginUserInterceptor implements HandlerInterceptor {

    public static ThreadLocal<MemberEntity> loginUser = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        MemberEntity member = (MemberEntity) session.getAttribute(AuthConstants.LOGIN_USER);
        if (member != null) {
            loginUser.set(member);
            return true;
        }
        // 没登录的话就要去登录
        else {
            session.setAttribute("msg", "请先进行登录");
            response.sendRedirect("http://auth.gulimall.com/login.html");
            return false;
        }
    }
}

