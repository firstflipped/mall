package com.laughingather.gulimall.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.laughingather.gulimall.admin.entity.SysRoleEntity;
import com.laughingather.gulimall.admin.entity.SysUserRoleEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户角色关联持久
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-19 17:32:23
 */
public interface SysUserRoleMapper extends BaseMapper<SysUserRoleEntity> {

    /**
     * 查询角色列表
     *
     * @param userid 用户id
     * @return
     */
    List<SysRoleEntity> listRolesByUserid(@Param("userid") Long userid);

}
