package com.laughingather.gulimall.admin.service.impl;

import cn.hutool.core.lang.Snowflake;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laughingather.gulimall.admin.entity.SysPermissionEntity;
import com.laughingather.gulimall.admin.entity.vo.PermissionsWithTreeVO;
import com.laughingather.gulimall.admin.mapper.SysPermissionMapper;
import com.laughingather.gulimall.admin.service.SysPermissionService;
import com.laughingather.gulimall.admin.util.SecurityUtil;
import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.common.constant.AdminConstants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 权限逻辑实现
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermissionEntity> implements SysPermissionService {

    @Resource
    private SysPermissionMapper sysPermissionMapper;
    @Resource
    private Snowflake snowflake;


    @Override
    public void savePermission(SysPermissionEntity sysPermissionEntity) {
        // 填入默认值
        sysPermissionEntity.setPermissionId(snowflake.nextId());
        sysPermissionEntity.setCreateBy(StringUtils.isNotBlank(SecurityUtil.getUsername()) ? SecurityUtil.getUsername() : "root");
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
        sysPermissionEntity.setUpdateBy(SecurityUtil.getUsername());
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

        return MyPage.restPage(permissionsWithPage);
    }

    @Override
    public List<PermissionsWithTreeVO> listPermissionsWithTree() {
        List<PermissionsWithTreeVO> permissionsWithTreeVOList = sysPermissionMapper.selectPermissionsVO();

        // 过滤出一级菜单
        List<PermissionsWithTreeVO> permissions1Level = permissionsWithTreeVOList.stream()
                .filter(item -> Objects.equals(AdminConstants.PERMISSION_ROOT_LEVEL, item.getParentId()))
                .collect(Collectors.toList());

        return permissions1Level.stream().peek(item -> {
            // 设置子类集合
            item.setChildren(getChild(permissionsWithTreeVOList, item.getPermissionId()));
        }).collect(Collectors.toList());
    }

    /**
     * 获取子类集合
     *
     * @param permissionsWithTreeVOList 权限列表
     * @param id                        id
     * @return
     */
    private List<PermissionsWithTreeVO> getChild(List<PermissionsWithTreeVO> permissionsWithTreeVOList, Long id) {

        // 防止空指针抛出异常
        return Optional.ofNullable(permissionsWithTreeVOList)
                .map(List::stream)
                .orElseGet(Stream::empty)
                .filter(item -> Objects.equals(id, item.getParentId()))
                .peek(item -> item.setChildren(getChild(permissionsWithTreeVOList, item.getPermissionId())))
                .collect(Collectors.toList());

    }
}

