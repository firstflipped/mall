package com.laughingather.gulimall.admin.service.impl;

import cn.hutool.core.lang.Snowflake;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laughingather.gulimall.admin.entity.SysRoleEntity;
import com.laughingather.gulimall.admin.mapper.SysRoleMapper;
import com.laughingather.gulimall.admin.service.SysRoleService;
import com.laughingather.gulimall.admin.util.SecurityUtil;
import com.laughingather.gulimall.common.api.MyPage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 角色逻辑实现
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRoleEntity> implements SysRoleService {

    @Resource
    private SysRoleMapper sysRoleMapper;
    @Resource
    private Snowflake snowflake;

    @Override
    public void saveRole(SysRoleEntity sysRoleEntity) {
        sysRoleEntity.setRoleId(snowflake.nextId());
        sysRoleEntity.setCreateBy(SecurityUtil.getUsername());
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
        sysRoleEntity.setUpdateBy(SecurityUtil.getUsername());
        sysRoleEntity.setUpdateTime(LocalDateTime.now());

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

