package com.flipped.mall.admin.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.flipped.mall.admin.entity.CustomUserDetails;
import com.flipped.mall.admin.entity.PermissionEntity;
import com.flipped.mall.admin.entity.UserEntity;
import com.flipped.mall.admin.service.CustomUserDetailsService;
import com.flipped.mall.admin.service.RolePermissionService;
import com.flipped.mall.admin.service.UserService;
import com.flipped.mall.common.constant.AdminConstants;
import com.flipped.mall.common.exception.UserNotExistException;
import com.flipped.mall.common.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
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
    private RolePermissionService rolePermissionService;
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 查询用户信息，如果用户信息不存在则直接返回
        UserEntity user = Boolean.TRUE.equals(redisTemplate.hasKey(AdminConstants.ADMIN_INFO + username)) ? getUserByRedis(username) : getUserByMysql(username);
        if (user == null) {
            throw new UserNotExistException("username is: " + username);
        }

        // 查询用户权限信息
        List<PermissionEntity> permissions = Boolean.TRUE.equals(redisTemplate.hasKey(AdminConstants.ADMIN_PERMISSION + username)) ? getPermissionsByRedis(username) : getPermissionsByMysql(user.getUserid(), username);

        // 返回自定义用户详情，包括用户信息与权限信息
        return new CustomUserDetails(user, permissions);
    }


    private UserEntity getUserByRedis(String username) {
        String userJson = redisTemplate.opsForValue().get(AdminConstants.ADMIN_INFO + username);
        return JsonUtil.string2Obj(userJson, UserEntity.class);
    }

    private UserEntity getUserByMysql(String username) {
        UserEntity user = userService.getUserByUsername(username);
        redisTemplate.opsForValue().set(AdminConstants.ADMIN_INFO + username, JsonUtil.obj2String(user));
        return user;
    }

    private List<PermissionEntity> getPermissionsByRedis(String username) {
        String permissionJson = redisTemplate.opsForValue().get(AdminConstants.ADMIN_PERMISSION + username);
        return JsonUtil.string2Obj(permissionJson, new TypeReference<List<PermissionEntity>>() {
        });
    }

    private List<PermissionEntity> getPermissionsByMysql(Long userid, String username) {
        // 查询权限信息
        List<PermissionEntity> permissions = rolePermissionService.listPermissionsByUserid(userid);
        redisTemplate.opsForValue().set(AdminConstants.ADMIN_PERMISSION + username, JsonUtil.obj2String(permissions));
        return permissions;
    }

}