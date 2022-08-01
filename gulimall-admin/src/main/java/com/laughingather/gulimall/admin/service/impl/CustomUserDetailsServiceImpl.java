package com.laughingather.gulimall.admin.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.laughingather.gulimall.admin.entity.CustomUserDetails;
import com.laughingather.gulimall.admin.entity.SysPermissionEntity;
import com.laughingather.gulimall.admin.entity.SysUserEntity;
import com.laughingather.gulimall.admin.service.CustomUserDetailsService;
import com.laughingather.gulimall.admin.service.SysRolePermissionService;
import com.laughingather.gulimall.admin.service.SysUserService;
import com.laughingather.gulimall.common.constant.AdminConstants;
import com.laughingather.gulimall.common.util.JsonUtil;
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
    private SysUserService sysUserService;
    @Resource
    private SysRolePermissionService sysRolePermissionService;
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 查询用户信息，如果用户信息不存在则直接返回
        SysUserEntity user = Boolean.TRUE.equals(redisTemplate.hasKey(AdminConstants.ADMIN_INFO + username)) ? getUserByRedis(username) : getUserByMysql(username);
        if (user == null) {
            return null;
        }

        // 查询用户权限信息
        List<SysPermissionEntity> permissions = Boolean.TRUE.equals(redisTemplate.hasKey(AdminConstants.ADMIN_PERMISSION + username)) ? getPermissionsByRedis(username) : getPermissionsByMysql(user.getUserid(), username);

        // 返回自定义用户详情，包括用户信息与权限信息
        return new CustomUserDetails(user, permissions);
    }


    private SysUserEntity getUserByRedis(String username) {
        String userJson = redisTemplate.opsForValue().get(AdminConstants.ADMIN_INFO + username);
        return JsonUtil.string2Obj(userJson, SysUserEntity.class);
    }

    private SysUserEntity getUserByMysql(String username) {
        SysUserEntity user = sysUserService.getUserByUsername(username);
        redisTemplate.opsForValue().set(AdminConstants.ADMIN_INFO + username, JsonUtil.obj2String(user));
        return user;
    }

    private List<SysPermissionEntity> getPermissionsByRedis(String username) {
        String permissionJson = redisTemplate.opsForValue().get(AdminConstants.ADMIN_PERMISSION + username);
        return JsonUtil.string2Obj(permissionJson, new TypeReference<List<SysPermissionEntity>>() {
        });
    }

    private List<SysPermissionEntity> getPermissionsByMysql(Long userid, String username) {
        // 查询权限信息
        List<SysPermissionEntity> permissions = sysRolePermissionService.listPermissionsByUserid(userid);
        redisTemplate.opsForValue().set(AdminConstants.ADMIN_PERMISSION + username, JsonUtil.obj2String(permissions));
        return permissions;
    }

}
