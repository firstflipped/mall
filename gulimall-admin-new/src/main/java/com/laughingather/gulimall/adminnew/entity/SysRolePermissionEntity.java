package com.laughingather.gulimall.adminnew.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "sys_role_permission")
public class SysRolePermissionEntity {
    /**
     *
     */
    @Id
    private Long id;

    /**
     * 角色id
     */
    @Column(name = "role_id")
    private Long roleId;

    /**
     * 权限id
     */
    @Column(name = "permission_id")
    private Long permissionId;

    /**
     * 数据权限ids
     */
    @Column(name = "data_rule_ids")
    private String dataRuleIds;

    /**
     * 操作时间
     */
    @Column(name = "operate_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private LocalDateTime operateDate;

    /**
     * 操作ip
     */
    @Column(name = "operate_ip")
    private Integer operateIp;
}

