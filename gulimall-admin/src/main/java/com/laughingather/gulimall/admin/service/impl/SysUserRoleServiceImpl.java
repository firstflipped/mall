package com.laughingather.gulimall.admin.service.impl;

import com.laughingather.gulimall.admin.entity.SysRoleEntity;
import com.laughingather.gulimall.admin.mapper.SysUserRoleMapper;
import com.laughingather.gulimall.admin.service.SysUserRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户角色关联逻辑实现
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-19 17:23:53
 */
@Service
public class SysUserRoleServiceImpl implements SysUserRoleService {

    @Resource
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public List<SysRoleEntity> listRolesByUserid(Long userid) {
        return sysUserRoleMapper.listRolesByUserid(userid);
    }
}
