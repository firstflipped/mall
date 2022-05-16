package com.laughingather.gulimall.admin.service.impl;

import cn.hutool.core.lang.Snowflake;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laughingather.gulimall.admin.entity.SysRoleEntity;
import com.laughingather.gulimall.admin.entity.SysUserRoleEntity;
import com.laughingather.gulimall.admin.entity.param.UserRolesParam;
import com.laughingather.gulimall.admin.mapper.SysUserRoleMapper;
import com.laughingather.gulimall.admin.service.SysUserRoleService;
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
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRoleEntity> implements SysUserRoleService {

    @Resource
    private Snowflake snowflake;
    @Resource
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public List<SysRoleEntity> listRolesByUserid(Long userid) {
        return sysUserRoleMapper.listRolesByUserid(userid);
    }

    @Override
    public void saveUserRoles(UserRolesParam userRolesParam) {
        Long userid = userRolesParam.getUserid();

        // 先删除用户对应角色
        QueryWrapper<SysUserRoleEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(SysUserRoleEntity::getUserid, userid);
        sysUserRoleMapper.delete(queryWrapper);

        // 添加用户对应角色集合（如果角色列表为空则表示清空用户角色列表）
        if (CollectionUtils.isNotEmpty(userRolesParam.getRoleIds())) {

            List<SysUserRoleEntity> userRoles = userRolesParam.getRoleIds().stream().map(roleId ->
                    SysUserRoleEntity.builder().id(snowflake.nextId()).userid(userid).roleId(roleId).build()).collect(Collectors.toList());
            saveBatch(userRoles);
        }

    }
}
