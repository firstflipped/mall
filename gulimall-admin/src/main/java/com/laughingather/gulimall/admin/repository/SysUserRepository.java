package com.laughingather.gulimall.admin.repository;

import com.laughingather.gulimall.admin.entity.SysUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 用户持久JPA
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
public interface SysUserRepository extends JpaRepository<SysUserEntity, Long> {

    /**
     * 根据用户名查询用户信息
     *
     * @param username
     * @return
     */
    SysUserEntity getByUsernameEquals(String username);

}