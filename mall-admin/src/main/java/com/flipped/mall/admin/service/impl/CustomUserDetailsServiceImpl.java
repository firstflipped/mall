package com.flipped.mall.admin.service.impl;

import com.flipped.mall.admin.entity.CustomUserDetails;
import com.flipped.mall.admin.entity.PermissionEntity;
import com.flipped.mall.admin.entity.UserEntity;
import com.flipped.mall.admin.service.CustomUserDetailsService;
import com.flipped.mall.admin.service.PermissionService;
import com.flipped.mall.admin.service.UserService;
import com.flipped.mall.common.exception.UserNotExistException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 自定义用户授权实现
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-19 10:08:20
 */
@Slf4j
@Service
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {

    @Resource
    private UserService userService;
    @Resource
    private PermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 查询数据库
        UserEntity user = userService.getUserByUsername(username);
        if (user == null) {
            throw new UserNotExistException("username is:" + username);
        }
        List<PermissionEntity> permissions = permissionService.listPermissionsByUserid(user.getUserid());
        return new CustomUserDetails(user, permissions);
    }

}
