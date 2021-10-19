package com.laughingather.gulimall.cart.interceptor;

import com.laughingather.gulimall.cart.entity.vo.UserInfoVO;
import com.laughingather.gulimall.common.constant.AuthConstants;
import com.laughingather.gulimall.common.constant.CartConstants;
import com.laughingather.gulimall.common.entity.MemberEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;
import java.util.UUID;

/**
 * 购物车拦截器
 * 在执行目标方法之前，判断用户的登录状态
 *
 * @author：laughingather
 * @create：2021-08-16 2021/8/16
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
        UserInfoVO userInfoVO = new UserInfoVO();

        HttpSession session = request.getSession();
        MemberEntity member = (MemberEntity) session.getAttribute(AuthConstants.LOGIN_USER);

        if (member != null) {
            // 用户没有登录
            userInfoVO.setUserId(member.getId());
        }

        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (Objects.equals(cookie.getName(), CartConstants.TEMP_USER_COOKIE_NAME)) {
                    userInfoVO.setUserKey(cookie.getValue());
                    userInfoVO.setTempUser(true);
                }
            }
        }

        if (StringUtils.isBlank(userInfoVO.getUserKey())) {
            userInfoVO.setUserKey(UUID.randomUUID().toString());
        }

        // 目标方法执行之前
        threadLocal.set(userInfoVO);

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
        UserInfoVO userInfoVO = threadLocal.get();
        // 如果是临时用户一定保存一个临时用户
        if (!userInfoVO.getTempUser()) {
            Cookie cookie = new Cookie(CartConstants.TEMP_USER_COOKIE_NAME, userInfoVO.getUserKey());
            cookie.setDomain("gulimall.com");
            cookie.setMaxAge(CartConstants.TEMP_USER_COOKIE_TIMEOUT);
            response.addCookie(cookie);
        }
    }
}

