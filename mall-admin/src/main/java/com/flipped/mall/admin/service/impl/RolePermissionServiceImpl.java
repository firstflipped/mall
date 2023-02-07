package com.flipped.mall.admin.service.impl;

import cn.hutool.core.lang.Snowflake;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.flipped.mall.admin.entity.PermissionEntity;
import com.flipped.mall.admin.entity.RolePermissionEntity;
import com.flipped.mall.admin.entity.param.RolePermissionParam;
import com.flipped.mall.admin.mapper.PermissionMapper;
import com.flipped.mall.admin.mapper.RolePermissionMapper;
import com.flipped.mall.admin.service.RolePermissionService;
import com.flipped.mall.common.constant.AdminConstants;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 角色权限关联逻辑实现
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-19 17:58:49
 */
@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermissionEntity> implements RolePermissionService {

    @Resource
    private Snowflake snowflake;
    @Resource
    private RolePermissionMapper rolePermissionMapper;
    @Resource
    private PermissionMapper permissionMapper;

    @Override
    public List<PermissionEntity> listPermissionsByUserid(Long userid) {
        // 如果为root权限则
        if (Objects.equals(userid, AdminConstants.ROOT_ID)) {
            return permissionMapper.selectList(null);
        }

        return rolePermissionMapper.listPermissionsByUserid(userid);
    }

    @Override
    public List<PermissionEntity> listPermissionsByRoleIds(List<Long> roleIds) {
        return rolePermissionMapper.listPermissionsByRoleIds(roleIds);
    }

    @Override
    public void saveRolePermissions(RolePermissionParam rolePermissionParam) {
        Long roleId = rolePermissionParam.getRoleId();

        // 先删除角色对应权限关系
        rolePermissionMapper.deleteByRoleId(roleId);

        if (CollectionUtils.isNotEmpty(rolePermissionParam.getPermissionIds())) {
            List<RolePermissionEntity> rolePermissions = rolePermissionParam.getPermissionIds().stream().map(permissionId ->
                    RolePermissionEntity.builder().id(snowflake.nextId()).roleId(roleId).permissionId(permissionId).build()
            ).collect(Collectors.toList());

            saveBatch(rolePermissions);
        }


    }
}
