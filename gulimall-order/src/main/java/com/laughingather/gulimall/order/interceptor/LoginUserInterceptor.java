package com.laughingather.gulimall.order.interceptor;

import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.common.entity.JwtPayLoad;
import com.laughingather.gulimall.common.util.TokenProvider;
import com.laughingather.gulimall.order.feign.entity.MemberTO;
import com.laughingather.gulimall.order.feign.service.MemberFeignService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录用户校验
 *
 * @author：laughingather
 * @create：2022-04-12 10:42
 */
public class LoginUserInterceptor implements HandlerInterceptor {

    @Resource
    private MemberFeignService memberFeignService;

    public static ThreadLocal<MemberTO> loginUser = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("");
        if (StringUtils.isNotBlank(token)) {
            // 解析token，获取会员信息
            JwtPayLoad jwtPayLoad = TokenProvider.getJwtPayLoad(token);
            Long userid = jwtPayLoad.getUserid();
            MyResult<MemberTO> memberResult = memberFeignService.getMember(userid);
            if (!memberResult.isSuccess()) {
                response.sendRedirect("https://auth.gulimall.com/login.html");
                return false;
            }


            loginUser.set(memberResult.getData());
            return true;
        }
        // 没登录的话就要去登录
        else {
            response.sendRedirect("https://auth.gulimall.com/login.html");
            return false;
        }
    }

}

