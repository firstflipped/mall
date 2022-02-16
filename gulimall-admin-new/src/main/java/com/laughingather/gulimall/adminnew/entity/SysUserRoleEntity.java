package com.laughingather.gulimall.adminnew.entity;

import lombok.Data;

/**
 * 用户角色关联实体
 *
 * @author laughingather
 */
@Data
public class SysUserRoleEntity {
    /**
     * 主键id
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userid;

    /**
     * 角色id
     */
    private Long roleId;
}

