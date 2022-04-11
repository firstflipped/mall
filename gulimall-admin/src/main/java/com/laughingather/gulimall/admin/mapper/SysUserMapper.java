package com.laughingather.gulimall.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.laughingather.gulimall.admin.entity.SysUserEntity;
import org.apache.ibatis.annotations.Param;

/**
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
public interface SysUserMapper extends BaseMapper<SysUserEntity> {

    /**
     * 更新用户删除字段状态
     *
     * @param userId 用户id
     * @param delete 删除字段状态
     */
    void updateUserDeleteById(@Param("userId") Long userId, @Param("delete") Integer delete);
}
