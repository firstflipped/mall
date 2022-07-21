package com.laughingather.gulimall.admin.entity;

import com.laughingather.gulimall.common.constant.AdminConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 自定义认证用户详情
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-19 10:10:00
 */
@Slf4j
public class CustomUserDetails implements UserDetails {

    /**
     * 用户信息
     */
    private SysUserEntity user;

    /**
     * 菜单列表
     */
    private List<SysPermissionEntity> permissions;

    public CustomUserDetails() {
    }

    public CustomUserDetails(SysUserEntity user, List<SysPermissionEntity> permissions) {
        this.user = user;
        this.permissions = permissions;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> permissionValues = permissions.stream()
                .filter(permission -> StringUtils.isNotBlank(permission.getPermissionValue()) && Objects.equals(permission.getStatus(), AdminConstants.ENABLE))
                .map(permission -> new SimpleGrantedAuthority(permission.getPermissionValue()))
                .collect(Collectors.toList());
        log.info("登录用户权限列表为{}", permissionValues);
        return permissionValues;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.getStatus().equals(1);
    }
}
