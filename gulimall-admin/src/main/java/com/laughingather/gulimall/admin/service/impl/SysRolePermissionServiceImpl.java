package com.laughingather.gulimall.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laughingather.gulimall.admin.entity.SysPermissionEntity;
import com.laughingather.gulimall.admin.entity.SysRolePermissionEntity;
import com.laughingather.gulimall.admin.mapper.SysRolePermissionMapper;
import com.laughingather.gulimall.admin.service.SysRolePermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
    private SysRolePermissionMapper sysRolePermissionMapper;

    @Override
    public List<SysPermissionEntity> listPermissionsByUserid(Long userid) {
        return sysRolePermissionMapper.listPermissionsByUserid(userid);
    }

    @Override
    public List<SysPermissionEntity> listPermissionsByRoleIds(List<Long> roleIds) {
        return sysRolePermissionMapper.listPermissionsByRoleIds(roleIds);
    }
}
