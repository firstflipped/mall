package com.flipped.mall.admin.service.impl;

import cn.hutool.core.lang.Snowflake;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.flipped.mall.admin.entity.RoleEntity;
import com.flipped.mall.admin.entity.UserRoleEntity;
import com.flipped.mall.admin.entity.param.UserRolesParam;
import com.flipped.mall.admin.mapper.UserRoleMapper;
import com.flipped.mall.admin.service.UserRoleService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户角色关联逻辑实现
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-19 17:23:53
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRoleEntity> implements UserRoleService {

    @Resource
    private Snowflake snowflake;
    @Resource
    private UserRoleMapper userRoleMapper;

    @Override
    public List<RoleEntity> listRolesByUserid(Long userid) {
        return userRoleMapper.listRolesByUserid(userid);
    }

    @Override
    public void saveUserRoles(UserRolesParam userRolesParam) {
        Long userid = userRolesParam.getUserid();

        // 先删除用户对应角色
        QueryWrapper<UserRoleEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(UserRoleEntity::getUserid, userid);
        userRoleMapper.delete(queryWrapper);

        // 添加用户对应角色集合（如果角色列表为空则表示清空用户角色列表）
        if (CollectionUtils.isNotEmpty(userRolesParam.getRoleIds())) {

            List<UserRoleEntity> userRoles = userRolesParam.getRoleIds().stream().map(roleId ->
                    UserRoleEntity.builder().id(snowflake.nextId()).userid(userid).roleId(roleId).build()).collect(Collectors.toList());
            saveBatch(userRoles);
        }

    }
}
