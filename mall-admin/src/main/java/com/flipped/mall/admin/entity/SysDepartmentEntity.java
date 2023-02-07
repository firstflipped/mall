package com.flipped.mall.admin.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * 权限实体
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-10-11 19:35:16
 */
@Data
@TableName("sys_department")
public class SysDepartmentEntity {

    /**
     * ID
     */
    @TableId
    private String departmentId;

    /**
     * 父机构ID
     */
    private String parentId;

    /**
     * 机构/部门名称
     */
    private String departmentName;

    /**
     * 缩写
     */
    private String departmentNameAbbr;

    /**
     * 排序
     */
    private Integer departmentOrder;

    /**
     * 描述
     */
    private String description;

    /**
     * 机构类别 1=公司，2=组织机构，3=岗位
     */
    private String departmentCategory;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 地址
     */
    private String address;

    /**
     * 状态（1启用，0不启用）
     */
    private String status;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 更新日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    /**
     * 部门负责人的ids
     */
    private Long directorUserId;
}
