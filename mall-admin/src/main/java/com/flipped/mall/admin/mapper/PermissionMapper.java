package com.flipped.mall.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.flipped.mall.admin.entity.PermissionEntity;
import com.flipped.mall.admin.entity.dto.AdminPermissionDTO;
import com.flipped.mall.admin.entity.vo.PermissionsWithTreeVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 权限持久层
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
public interface PermissionMapper extends BaseMapper<PermissionEntity> {

    /**
     * 单条更新权限启用/关闭状态
     *
     * @param permissionId 权限id
     * @param enable       启用状态
     */
    void updatePermissionEnable(@Param("permissionId") Long permissionId, @Param("enable") Integer enable);

    /**
     * 查询树形结构权限列表
     *
     * @return 树形结构列表
     */
    List<PermissionsWithTreeVO> selectPermissionsVO();

    /**
     * 查询权限列表
     *
     * @param userid 用户id
     * @return 用户的权限列表
     */
    List<PermissionEntity> listPermissionsByUserid(@Param("userid") Long userid);

    /**
     * 查询树形权限列表
     *
     * @return
     */
    List<AdminPermissionDTO> listPermissionsWithTreeByUserid();

    /**
     * 查询树形权限列表
     *
     * @param userid
     * @return
     */
    List<AdminPermissionDTO> listPermissionsWithTreeByUserid(@Param("userid") Long userid);
}
