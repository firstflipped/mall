package com.flipped.mall.admin.util;

import com.flipped.mall.admin.entity.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Security上下文工具类
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-06-14 11:04:18
 */
public class SecurityUtil {

    /**
     * 获取用户名
     *
     * @return 用户名
     */
    public static String getUsername() {
        return getLoginUser() != null ? getLoginUser().getUsername() : null;
    }


    /**
     * 获取当前登录用户信息
     *
     * @return 自定义用户信息
     */
    public static CustomUserDetails getLoginUser() {
        return getAuthentication() != null ? (CustomUserDetails) getAuthentication().getPrincipal() : null;
    }


    /**
     * 获取Authentication上下文信息
     *
     * @return Authentication
     */
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}

