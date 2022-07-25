package com.laughingather.gulimall.admin.service.impl;

import com.laughingather.gulimall.admin.entity.CustomUserDetails;
import com.laughingather.gulimall.admin.entity.SysPermissionEntity;
import com.laughingather.gulimall.admin.entity.SysUserEntity;
import com.laughingather.gulimall.admin.service.CustomUserDetailsService;
import com.laughingather.gulimall.admin.service.SysRolePermissionService;
import com.laughingather.gulimall.admin.service.SysUserService;
import com.laughingather.gulimall.common.constant.AuthConstants;
import com.laughingather.gulimall.common.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
    private RedisTemplate<String, String> redisTemplate;
    @Resource
    private SysUserService sysUserService;
    @Resource
    private SysRolePermissionService sysRolePermissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO: 加入redis缓存登陆用户信息
        String customUserDetailsJson = redisTemplate.opsForValue().get(username);
        if (StringUtils.isNotBlank(customUserDetailsJson)) {
            return JsonUtil.string2Obj(customUserDetailsJson, CustomUserDetails.class);
        }

        SysUserEntity user = sysUserService.getUserByUsername(username);
        if (user == null) {
            return null;
        }

        log.info("{}", user);

        // 返回自定义用户详情，包括用户信息与权限信息
        List<SysPermissionEntity> permissions = sysRolePermissionService.listPermissionsByUserid(user.getUserid());
        CustomUserDetails customUserDetails = new CustomUserDetails(user, permissions);
        redisTemplate.opsForValue().set(username, JsonUtil.obj2String(customUserDetails), AuthConstants.TOKEN_EXP_TIME, TimeUnit.SECONDS);
        return customUserDetails;
    }

}
