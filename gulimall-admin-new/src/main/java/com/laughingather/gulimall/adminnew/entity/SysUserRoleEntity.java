package com.laughingather.gulimall.adminnew.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "sys_user_role")
public class SysUserRoleEntity {
    /**
     * 主键id
     */
    @Id
    private Long id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Long userid;

    /**
     * 角色id
     */
    @Column(name = "role_id")
    private Long roleId;
}

