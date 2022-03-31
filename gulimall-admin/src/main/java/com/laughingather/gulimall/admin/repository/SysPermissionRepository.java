package com.laughingather.gulimall.admin.repository;

import com.laughingather.gulimall.admin.entity.SysPermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 权限持久JPA
 *
 * @author：laughingather
 * @create：2021-12-01 2021/12/1
 */
public interface SysPermissionRepository extends JpaRepository<SysPermissionEntity, Long> {
}