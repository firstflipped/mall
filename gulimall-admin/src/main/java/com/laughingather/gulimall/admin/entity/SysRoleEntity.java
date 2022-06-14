package com.laughingather.gulimall.admin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.laughingather.gulimall.common.valid.AddGroup;
import com.laughingather.gulimall.common.valid.UpdateGroup;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.time.LocalDateTime;

/**
 * 角色实体
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Data
@TableName(value = "sys_role")
public class SysRoleEntity {
    /**
     * 主键id
     */
    @Id
    @Null(message = "新增时角色id必须为空", groups = AddGroup.class)
    @NotNull(message = "更新时角色id不能为空", groups = UpdateGroup.class)
    private Long roleId;

    /**
     * 角色名称
     */
    @NotBlank(message = "角色名称不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String roleName;

    /**
     * 角色编码
     */
    @NotBlank(message = "角色编码不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String roleCode;

    /**
     * 描述
     */
    private String description;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 更新时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}

