package com.laughingather.gulimall.admin.repository;

import com.laughingather.gulimall.admin.entity.SysRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 角色持久层
 *
 * @author：laughingather
 * @create：2021-11-25 2021/11/25
 */
public interface SysRoleRepository extends JpaRepository<SysRoleEntity, Long> {
}