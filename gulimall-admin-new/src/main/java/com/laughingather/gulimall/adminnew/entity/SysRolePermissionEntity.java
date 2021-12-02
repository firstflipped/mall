package com.laughingather.gulimall.adminnew.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class SysRolePermissionEntity {
    /**
     *
     */
    private Long id;

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 权限id
     */
    private Long permissionId;

    /**
     * 数据权限ids
     */
    private String dataRuleIds;

    /**
     * 操作时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private LocalDateTime operateDate;

    /**
     * 操作ip
     */
    private Integer operateIp;
}

