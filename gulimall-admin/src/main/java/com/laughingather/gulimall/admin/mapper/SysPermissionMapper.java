package com.laughingather.gulimall.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.laughingather.gulimall.admin.entity.SysPermissionEntity;
import com.laughingather.gulimall.admin.entity.vo.PermissionsWithTreeVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 权限持久Mybatis
 *
 * @author：laughingather
 * @create：2021-12-01 2021/12/1
 */
public interface SysPermissionMapper extends BaseMapper<SysPermissionEntity> {

    /**
     * 批量更新权限删除状态
     *
     * @param permissionIds 权限id集合
     * @param status        权限删除状态
     */
    void batchUpdatePermissionDelete(@Param("permissionIds") List<Long> permissionIds, @Param("status") Integer status);

    /**
     * 单条更新权限删除状态
     *
     * @param permissionId
     * @param status
     */
    void updatePermissionDelete(@Param("permissionId") Long permissionId, @Param("status") Integer status);

    /**
     * 查询树形展示结构列表
     *
     * @return
     */
    List<PermissionsWithTreeVO> selectPermissionsVO();
}
