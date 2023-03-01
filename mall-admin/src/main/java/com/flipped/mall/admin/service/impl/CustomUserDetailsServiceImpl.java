package com.flipped.mall.admin.service.impl;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
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
import org.apache.commons.lang3.StringUtils;
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

        CustomUserDetails customUserDetails;
        // 先取缓存，缓存中存在直接缓存数据返回
        String customUserDetailsJson = redisTemplate.opsForValue().get(AdminConstants.ADMIN_INFO + username);
        if (StringUtils.isNotBlank(customUserDetailsJson)) {
            customUserDetails = jsonToCustomUserDetails(customUserDetailsJson);
            return customUserDetails;
        }

        // 缓存中不存在，则查询数据库并将数据进行混缓存
        UserEntity user = userService.getUserByUsername(username);
        if (user == null) {
            throw new UserNotExistException("username is:" + username);
        }
        List<PermissionEntity> permissions = rolePermissionService.listPermissionsByUserid(user.getUserid());
        customUserDetails = new CustomUserDetails(user, permissions);
        // 存入redis
        redisTemplate.opsForValue().set(AdminConstants.ADMIN_INFO + username, JsonUtil.bean2Json(customUserDetails));
        return customUserDetails;
    }


    public CustomUserDetails jsonToCustomUserDetails(String customUserDetailsJson) {
        CustomUserDetails customUserDetails = null;
        try {
            // 将 json 格式的字符串转换成 JSONObject 对象
            JSONObject customUserDetailsJSONObject = JSONObject.parseObject(customUserDetailsJson);
            // 简单的直接获取值
            JSONObject userJSONObject = customUserDetailsJSONObject.getJSONObject("user");
            UserEntity user = JsonUtil.json2Bean(userJSONObject.toJSONString(), UserEntity.class);
            // 如果 json 格式的字符串里含有数组格式的属性，将其转换成 JSONArray ，以方便后面转换成对应的实体
            JSONArray permissionJSONArray = customUserDetailsJSONObject.getJSONArray("permissions");
            List<PermissionEntity> permissions = JsonUtil.json2BeanList(permissionJSONArray.toJSONString(), List.class, PermissionEntity.class);

            customUserDetails = new CustomUserDetails();
            customUserDetails.setUser(user);
            customUserDetails.setPermissions(permissions);
        } catch (Exception e) {
            log.error("============= json转换成实体类出错，用户拦截获取缓存信息失败 =============\n 错误信息：{}", e.getMessage());
            e.printStackTrace();
        }
        return customUserDetails;
    }

}
