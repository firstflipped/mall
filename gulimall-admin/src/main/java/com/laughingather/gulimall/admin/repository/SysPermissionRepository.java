package com.laughingather.gulimall.admin.repository;

import com.laughingather.gulimall.admin.entity.SysPermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 权限持久JPA
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
public interface SysPermissionRepository extends JpaRepository<SysPermissionEntity, Long> {
}