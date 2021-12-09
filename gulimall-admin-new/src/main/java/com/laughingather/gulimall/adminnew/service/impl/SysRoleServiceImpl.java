package com.laughingather.gulimall.adminnew.service.impl;

import cn.hutool.core.lang.Snowflake;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laughingather.gulimall.adminnew.entity.SysRoleEntity;
import com.laughingather.gulimall.adminnew.mapper.SysRoleMapper;
import com.laughingather.gulimall.adminnew.service.SysRoleService;
import com.laughingather.gulimall.common.api.MyPage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 角色逻辑实现
 *
 * @author：laughingather
 * @create：2021-11-25 2021/11/25
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Resource
    private SysRoleMapper sysRoleMapper;
    @Resource
    private Snowflake snowflake;

    @Override
    public void saveRole(SysRoleEntity sysRoleEntity) {
        sysRoleEntity.setId(snowflake.nextId());
        sysRoleEntity.setCreateTime(LocalDateTime.now());

        sysRoleMapper.insert(sysRoleEntity);
    }

    @Override
    public void deleteBatchRoleByIds(List<Long> roleIds) {
        sysRoleMapper.deleteBatchIds(roleIds);
    }

    @Override
    public void deleteRoleById(Long roleId) {
        sysRoleMapper.deleteById(roleId);
    }

    @Override
    public void updateRoleById(SysRoleEntity sysRoleEntity) {
        sysRoleMapper.updateById(sysRoleEntity);
    }

    @Override
    public List<SysRoleEntity> listRoles() {
        return sysRoleMapper.selectList(null);
    }

    @Override
    public MyPage<SysRoleEntity> listRolesWithPage(Integer pageNum, Integer pageSize) {
        IPage<SysRoleEntity> sysRolePage = sysRoleMapper.selectPage(new Page<>(pageNum, pageSize), null);
        return MyPage.restPage(sysRolePage);
    }


}

