package com.laughingather.gulimall.admin.service.impl;

import cn.hutool.core.lang.Snowflake;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laughingather.gulimall.admin.entity.SysPermissionEntity;
import com.laughingather.gulimall.admin.entity.SysRolePermissionEntity;
import com.laughingather.gulimall.admin.entity.param.RolePermissionParam;
import com.laughingather.gulimall.admin.mapper.SysPermissionMapper;
import com.laughingather.gulimall.admin.mapper.SysRolePermissionMapper;
import com.laughingather.gulimall.admin.service.SysRolePermissionService;
import com.laughingather.gulimall.common.constant.AdminConstants;
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
public class SysRolePermissionServiceImpl extends ServiceImpl<SysRolePermissionMapper, SysRolePermissionEntity> implements SysRolePermissionService {

    @Resource
    private Snowflake snowflake;
    @Resource
    private SysRolePermissionMapper sysRolePermissionMapper;
    @Resource
    private SysPermissionMapper sysPermissionMapper;

    @Override
    public List<SysPermissionEntity> listPermissionsByUserid(Long userid) {
        // 如果为root权限则
        if (Objects.equals(userid, AdminConstants.ROOT_ID)) {
            return sysPermissionMapper.selectList(null);
        }

        return sysRolePermissionMapper.listPermissionsByUserid(userid);
    }

    @Override
    public List<SysPermissionEntity> listPermissionsByRoleIds(List<Long> roleIds) {
        return sysRolePermissionMapper.listPermissionsByRoleIds(roleIds);
    }

    @Override
    public void saveRolePermissions(RolePermissionParam rolePermissionParam) {
        Long roleId = rolePermissionParam.getRoleId();

        // 先删除角色对应权限关系
        sysRolePermissionMapper.deleteByRoleId(roleId);

        if (CollectionUtils.isNotEmpty(rolePermissionParam.getPermissionIds())) {
            List<SysRolePermissionEntity> rolePermissions = rolePermissionParam.getPermissionIds().stream().map(permissionId ->
                    SysRolePermissionEntity.builder().id(snowflake.nextId()).roleId(roleId).permissionId(permissionId).build()
            ).collect(Collectors.toList());

            saveBatch(rolePermissions);
        }


    }
}
