package com.flipped.mall.admin.entity;

import com.flipped.mall.common.constant.AdminConstants;
import lombok.Setter;
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
@Setter
public class CustomUserDetails implements UserDetails {

    /**
     * 用户信息
     */
    private UserEntity user;

    /**
     * 菜单列表
     */
    private List<PermissionEntity> permissions;

    public CustomUserDetails() {
    }

    public CustomUserDetails(UserEntity user, List<PermissionEntity> permissions) {
        this.user = user;
        this.permissions = permissions;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return permissions.stream()
                .filter(permission -> StringUtils.isNotBlank(permission.getPermissionValue()) && Objects.equals(permission.getEnable(), AdminConstants.ENABLE))
                .map(permission -> new SimpleGrantedAuthority(permission.getPermissionValue()))
                .collect(Collectors.toList());
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
        return user.getEnable().equals(AdminConstants.ENABLE);
    }
}
