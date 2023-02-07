package com.flipped.mall.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.flipped.mall.admin.entity.RoleEntity;
import com.flipped.mall.admin.entity.UserRoleEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户角色关联持久
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-19 17:32:23
 */
public interface UserRoleMapper extends BaseMapper<UserRoleEntity> {

    /**
     * 查询角色列表
     *
     * @param userid 用户id
     * @return 角色列表
     */
    List<RoleEntity> listRolesByUserid(@Param("userid") Long userid);

}
