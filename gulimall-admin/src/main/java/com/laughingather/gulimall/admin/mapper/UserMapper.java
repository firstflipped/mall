package com.laughingather.gulimall.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.laughingather.gulimall.admin.entity.UserEntity;
import org.apache.ibatis.annotations.Param;

/**
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
public interface UserMapper extends BaseMapper<UserEntity> {

    /**
     * 更新用户状态
     *
     * @param userid 用户id
     * @param enable 用户状态字段
     */
    void updateUserStatusById(@Param("userid") Long userid, @Param("enable") Integer enable);

    /**
     * 更新用户密码
     *
     * @param userid   用户id
     * @param password 密码
     */
    void updateUserPasswordById(@Param("userid") Long userid, @Param("password") String password);
}
