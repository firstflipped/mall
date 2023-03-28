package com.flipped.mall.admin.service.impl;

import cn.hutool.core.lang.Snowflake;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.flipped.mall.admin.entity.PermissionEntity;
import com.flipped.mall.admin.entity.dto.AdminPermissionDTO;
import com.flipped.mall.admin.entity.param.PermissionEnableParam;
import com.flipped.mall.admin.entity.query.PermissionQuery;
import com.flipped.mall.admin.entity.vo.PermissionsWithTreeVO;
import com.flipped.mall.admin.mapper.PermissionMapper;
import com.flipped.mall.admin.service.PermissionService;
import com.flipped.mall.admin.util.SecurityUtil;
import com.flipped.mall.common.constant.AdminConstants;
import com.flipped.mall.common.entity.api.MyPage;
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
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, PermissionEntity> implements PermissionService {

    @Resource
    private PermissionMapper permissionMapper;
    @Resource
    private Snowflake snowflake;


    @Override
    public void savePermission(PermissionEntity permissionEntity) {
        // 填入默认值
        permissionEntity.setPermissionId(snowflake.nextId());
        permissionEntity.setCreateBy(StringUtils.isNotBlank(SecurityUtil.getUsername()) ? SecurityUtil.getUsername() : "root");
        permissionEntity.setEnable(AdminConstants.ENABLE);

        permissionMapper.insert(permissionEntity);
    }

    @Override
    public void batchDeletePermission(List<Long> permissionIds) {
        // 修改为未启用
        permissionMapper.deleteBatchIds(permissionIds);
    }

    @Override
    public void deletePermission(Long permissionId) {
        permissionMapper.deleteById(permissionId);
    }

    @Override
    public void updatePermission(PermissionEntity permissionEntity) {
        permissionEntity.setUpdateBy(SecurityUtil.getUsername());
        permissionEntity.setUpdateTime(LocalDateTime.now());

        permissionMapper.updateById(permissionEntity);
    }

    @Override
    public void enableOrClosePermission(PermissionEnableParam permissionEnableParam) {
        permissionMapper.updatePermissionEnable(permissionEnableParam.getPermissionId(), permissionEnableParam.getEnable());
    }

    @Override
    public List<PermissionEntity> listPermissions() {
        return permissionMapper.selectList(null);
    }

    @Override
    public MyPage<PermissionEntity> listPermissionsWithPage(PermissionQuery permissionQuery) {
        // 分页
        IPage<PermissionEntity> page = new Page<>(permissionQuery.getPn(), permissionQuery.getPs());
        // 排序
        LambdaQueryWrapper<PermissionEntity> queryWrapper = new LambdaQueryWrapper<>();
        if (!Objects.isNull(permissionQuery.getParentId())) {
            queryWrapper.eq(PermissionEntity::getParentId, permissionQuery.getParentId());
        }
        if (!Objects.isNull(permissionQuery.getType())) {
            queryWrapper.eq(PermissionEntity::getType, permissionQuery.getType());
        }
        queryWrapper.orderByAsc(PermissionEntity::getSort);

        IPage<PermissionEntity> permissionsPage = permissionMapper.selectPage(page, queryWrapper);
        return MyPage.restPage(permissionsPage);
    }

    @Override
    public List<PermissionsWithTreeVO> listPermissionsWithTree() {
        List<PermissionsWithTreeVO> permissionsWithTreeVOList = permissionMapper.selectPermissionsVO();

        // 过滤出一级菜单
        List<PermissionsWithTreeVO> permissions1Level = permissionsWithTreeVOList.stream()
                .filter(item -> Objects.equals(AdminConstants.PERMISSION_ROOT_LEVEL, item.getParentId()))
                .collect(Collectors.toList());

        return permissions1Level.stream().peek(item -> {
            // 设置子类集合
            item.setChildren(getChild(permissionsWithTreeVOList, item.getPermissionId()));
        }).collect(Collectors.toList());
    }

    @Override
    public List<PermissionEntity> listPermissionsByUserid(Long userid) {
        // 如果为root权限则
        if (Objects.equals(userid, AdminConstants.ROOT_ID)) {
            return permissionMapper.selectList(null);
        }

        return permissionMapper.listPermissionsByUserid(userid);
    }

    @Override
    public List<AdminPermissionDTO> listPermissionsWithTreeByUserid(Long userid) {
        List<AdminPermissionDTO> adminPermissionDTOList;
        if (Objects.equals(userid, AdminConstants.ROOT_ID)) {
            adminPermissionDTOList = permissionMapper.listPermissionsWithTreeByUserid();
        } else {
            adminPermissionDTOList = permissionMapper.listPermissionsWithTreeByUserid(userid);
        }
        // 过滤出一级菜单
        List<AdminPermissionDTO> permissions1Level = adminPermissionDTOList.stream()
                .filter(item -> Objects.equals(AdminConstants.PERMISSION_ROOT_LEVEL, item.getParentId()))
                .collect(Collectors.toList());

        return permissions1Level.stream().peek(item -> {
            // 设置子类集合
            item.setChildren(getChildDTO(adminPermissionDTOList, item.getPermissionId()));
        }).collect(Collectors.toList());
    }

    /**
     * 获取子类集合
     *
     * @param permissionsWithTreeVOList 权限列表
     * @param id                        id
     * @return 权限数集合
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

    /**
     * 获取子类集合
     *
     * @param adminPermissionDTOList 权限列表
     * @param id                     id
     * @return 权限数集合
     */
    private List<AdminPermissionDTO> getChildDTO(List<AdminPermissionDTO> adminPermissionDTOList, Long id) {

        // 防止空指针抛出异常
        return Optional.ofNullable(adminPermissionDTOList)
                .map(List::stream)
                .orElseGet(Stream::empty)
                .filter(item -> Objects.equals(id, item.getParentId()))
                .peek(item -> item.setChildren(getChildDTO(adminPermissionDTOList, item.getPermissionId())))
                .collect(Collectors.toList());

    }
}

