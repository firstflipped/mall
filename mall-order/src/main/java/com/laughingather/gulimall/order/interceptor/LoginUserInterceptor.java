package com.laughingather.gulimall.order.interceptor;

import com.flipped.mall.common.entity.api.MyResult;
import com.flipped.mall.common.entity.JwtPayLoad;
import com.flipped.mall.common.util.TokenProvider;
import com.laughingather.gulimall.order.feign.entity.MemberDTO;
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

    public static ThreadLocal<MemberDTO> loginUser = new ThreadLocal<>();
    @Resource
    private MemberFeignService memberFeignService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("");
        if (StringUtils.isNotBlank(token)) {
            // 解析token，获取会员信息
            JwtPayLoad jwtPayLoad = TokenProvider.getJwtPayLoad(token);
            Long userid = jwtPayLoad.getUserid();
            MyResult<MemberDTO> memberResult = memberFeignService.getMember(userid);
            if (!memberResult.getSuccess()) {
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

