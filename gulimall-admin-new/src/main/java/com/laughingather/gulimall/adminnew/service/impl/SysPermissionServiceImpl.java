package com.laughingather.gulimall.adminnew.service.impl;

import cn.hutool.core.lang.Snowflake;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laughingather.gulimall.adminnew.entity.SysPermissionEntity;
import com.laughingather.gulimall.adminnew.entity.vo.PermissionsWithTreeVO;
import com.laughingather.gulimall.adminnew.mapper.SysPermissionMapper;
import com.laughingather.gulimall.adminnew.service.SysPermissionService;
import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.common.constant.AdminConstants;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 权限逻辑实现
 *
 * @author：laughingather
 * @create：2021-12-01 2021/12/1
 */
@Service
public class SysPermissionServiceImpl implements SysPermissionService {

    @Resource
    private SysPermissionMapper sysPermissionMapper;
    @Resource
    private Snowflake snowflake;


    @Override
    public void savePermission(SysPermissionEntity sysPermissionEntity) {
        // 填入默认值
        sysPermissionEntity.setId(snowflake.nextId());
        sysPermissionEntity.setDelete(AdminConstants.NO);
        sysPermissionEntity.setCreateTime(LocalDateTime.now());

        sysPermissionMapper.insert(sysPermissionEntity);
    }

    @Override
    public void batchDeletePermission(List<Long> permissionIds) {
        // 修改删除标志位为已删除（逻辑删除）
        sysPermissionMapper.batchUpdatePermissionDelete(permissionIds, AdminConstants.YES);
    }

    @Override
    public void deletePermission(Long permissionId) {
        sysPermissionMapper.updatePermissionDelete(permissionId, AdminConstants.YES);
    }

    @Override
    public void updatePermission(SysPermissionEntity sysPermissionEntity) {
        sysPermissionEntity.setUpdateTime(LocalDateTime.now());

        sysPermissionMapper.updateById(sysPermissionEntity);
    }

    @Override
    public List<SysPermissionEntity> listPermissions() {
        return sysPermissionMapper.selectList(null);
    }

    @Override
    public MyPage<SysPermissionEntity> listPermissionsWithPage(Integer pageNum, Integer pageSize) {
        // 数据库查询从0开始
        IPage<SysPermissionEntity> permissionsWithPage = sysPermissionMapper.selectPage(new Page<>(pageNum, pageSize), null);

        MyPage<SysPermissionEntity> permissionsWithMyPage = MyPage.restPage(permissionsWithPage);
        return permissionsWithMyPage;
    }

    @Override
    public List<PermissionsWithTreeVO> listPermissionsWithTree() {
        List<PermissionsWithTreeVO> permissionsWithTreeVOList = sysPermissionMapper.selectPermissionsVO();

        // 过滤出一级菜单
        List<PermissionsWithTreeVO> permissions1Level = permissionsWithTreeVOList.stream()
                .filter(item -> Objects.equals(AdminConstants.PERMISSION_ROOT_LEVEL, item.getParentId()))
                .collect(Collectors.toList());

        List<PermissionsWithTreeVO> permissionsWithTree = permissions1Level.stream().map(item -> {
            // 设置子类集合
            item.setChildren(getChild(permissionsWithTreeVOList, item.getId()));
            return item;
        }).collect(Collectors.toList());

        return permissionsWithTree;
    }

    /**
     * 获取子类集合
     *
     * @param permissionsWithTreeVOList
     * @param id
     * @return
     */
    private List<PermissionsWithTreeVO> getChild(List<PermissionsWithTreeVO> permissionsWithTreeVOList, Long id) {

        return permissionsWithTreeVOList.stream()
                .filter(item -> Objects.equals(id, item.getParentId()))
                .map(item -> {
                    item.setChildren(getChild(permissionsWithTreeVOList, item.getId()));
                            return item;
                        }
                ).collect(Collectors.toList());

    }
}

