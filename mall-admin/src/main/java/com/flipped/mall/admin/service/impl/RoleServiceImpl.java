package com.flipped.mall.admin.service.impl;

import cn.hutool.core.lang.Snowflake;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.flipped.mall.admin.entity.RoleEntity;
import com.flipped.mall.admin.mapper.RoleMapper;
import com.flipped.mall.admin.service.RoleService;
import com.flipped.mall.admin.util.SecurityUtil;
import com.laughingather.gulimall.common.entity.api.MyPage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色逻辑实现
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, RoleEntity> implements RoleService {

    @Resource
    private RoleMapper roleMapper;
    @Resource
    private Snowflake snowflake;

    @Override
    public void saveRole(RoleEntity roleEntity) {
        roleEntity.setRoleId(snowflake.nextId());
        roleEntity.setCreateBy(SecurityUtil.getUsername());

        roleMapper.insert(roleEntity);
    }

    @Override
    public void deleteBatchRoleByIds(List<Long> roleIds) {
        roleMapper.deleteBatchIds(roleIds);
    }

    @Override
    public void deleteRoleById(Long roleId) {
        roleMapper.deleteById(roleId);
    }

    @Override
    public void updateRoleById(RoleEntity roleEntity) {
        roleEntity.setUpdateBy(SecurityUtil.getUsername());

        roleMapper.updateById(roleEntity);
    }

    @Override
    public List<RoleEntity> listRoles() {
        return roleMapper.selectList(null);
    }

    @Override
    public MyPage<RoleEntity> listRolesWithPage(Integer pageNum, Integer pageSize) {
        IPage<RoleEntity> rolesPage = roleMapper.selectPage(Page.of(pageNum, pageSize), null);
        return MyPage.restPage(rolesPage);
    }


}

