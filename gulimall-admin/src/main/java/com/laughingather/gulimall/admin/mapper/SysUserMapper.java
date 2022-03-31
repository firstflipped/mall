package com.laughingather.gulimall.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.laughingather.gulimall.admin.entity.SysUserEntity;
import org.apache.ibatis.annotations.Param;

/**
 * @author：laughingather
 * @create：2021-11-26 2021/11/26
 */
public interface SysUserMapper extends BaseMapper<SysUserEntity> {

    /**
     * 更新用户删除字段状态
     *
     * @param userId
     * @param delete
     */
    void updateUserDeleteById(@Param("userId") Long userId, @Param("delete") Integer delete);
}
