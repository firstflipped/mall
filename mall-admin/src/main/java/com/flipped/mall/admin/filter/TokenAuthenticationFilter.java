package com.flipped.mall.admin.filter;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.flipped.mall.admin.entity.CustomUserDetails;
import com.flipped.mall.admin.entity.PermissionEntity;
import com.flipped.mall.admin.entity.UserEntity;
import com.flipped.mall.common.constant.AdminConstants;
import com.flipped.mall.common.constant.AuthConstants;
import com.flipped.mall.common.entity.JwtPayLoad;
import com.flipped.mall.common.util.JsonUtil;
import com.flipped.mall.common.util.TokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 用户信息拦截器
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Slf4j
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        // 登录等接口没有token，配置白名单后边会直接放行，由下一个链抛出异常
        // 如果token体为空，则直接放行，交由后边的过滤器处理
        String token = getTokenFromHttpRequest(request);
        if (StringUtils.isBlank(token)) {
            chain.doFilter(request, response);
            return;
        }
        JwtPayLoad jwtPayLoad = TokenProvider.getJwtPayLoad(token);
        if (jwtPayLoad == null) {
            chain.doFilter(request, response);
            return;
        }

        request.setAttribute(AuthConstants.USERID, jwtPayLoad.getUserid());
        request.setAttribute(AuthConstants.USERNAME, jwtPayLoad.getUsername());

        // 从缓存中获取用户信息及权限
        String customUserDetailsJson = redisTemplate.opsForValue().get(AdminConstants.ADMIN_INFO + jwtPayLoad.getUsername());
        CustomUserDetails customUserDetails = jsonToCustomUserDetails(customUserDetailsJson);
        if (customUserDetails != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            // 获取用户信息，并将用户信息存储到安全上下文中
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        chain.doFilter(request, response);
    }


    /**
     * json转换为对象
     *
     * @param customUserDetailsJson json串
     * @return CustomUserDetails
     */
    private CustomUserDetails jsonToCustomUserDetails(String customUserDetailsJson) {
        if (StringUtils.isBlank(customUserDetailsJson)) {
            return null;
        }

        // 将 json 格式的字符串转换成 JSONObject 对象
        JSONObject customUserDetailsJSONObject = JSONObject.parseObject(customUserDetailsJson);
        // 简单的直接获取值
        JSONObject userJSONObject = customUserDetailsJSONObject.getJSONObject("user");
        UserEntity user = JsonUtil.json2Bean(userJSONObject.toJSONString(), UserEntity.class);
        // 如果 json 格式的字符串里含有数组格式的属性，将其转换成 JSONArray ，以方便后面转换成对应的实体
        JSONArray permissionJSONArray = customUserDetailsJSONObject.getJSONArray("permissions");
        List<PermissionEntity> permissions = JsonUtil.json2BeanList(permissionJSONArray.toJSONString(), List.class, PermissionEntity.class);

        CustomUserDetails customUserDetails = new CustomUserDetails();
        customUserDetails.setUser(user);
        customUserDetails.setPermissions(permissions);
        return customUserDetails;
    }

    /**
     * 从请求头中获取token
     *
     * @param request 请求
     * @return token
     */
    private String getTokenFromHttpRequest(HttpServletRequest request) {
        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorization == null || !authorization.startsWith(AuthConstants.TOKEN_PREFIX)) {
            return null;
        }
        // 去掉 token 前缀
        return authorization.replace(AuthConstants.TOKEN_PREFIX, "");
    }

}