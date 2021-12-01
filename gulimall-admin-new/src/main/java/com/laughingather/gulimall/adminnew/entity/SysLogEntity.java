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
@Table(name = "sys_log")
public class SysLogEntity {
    /**
     *
     */
    @Id
    private Long id;

    /**
     * 日志类型（1登录日志，2操作日志）
     */
    @Column(name = "log_type")
    private Boolean logType;

    /**
     * 日志内容
     */
    @Column(name = "log_content")
    private String logContent;

    /**
     * 操作类型
     */
    @Column(name = "operate_type")
    private Integer operateType;

    /**
     * 操作用户账号
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 操作用户名称
     */
    @Column(name = "username")
    private String username;

    /**
     * IP
     */
    @Column(name = "ip")
    private Integer ip;

    /**
     * 请求java方法
     */
    @Column(name = "method")
    private String method;

    /**
     * 请求路径
     */
    @Column(name = "request_url")
    private String requestUrl;

    /**
     * 请求参数
     */
    @Column(name = "request_param")
    private String requestParam;

    /**
     * 请求类型
     */
    @Column(name = "request_type")
    private String requestType;

    /**
     * 耗时
     */
    @Column(name = "cost_time")
    private Long costTime;

    /**
     * 创建人
     */
    @Column(name = "create_by")
    private String createBy;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private LocalDateTime createTime;

    /**
     * 更新人
     */
    @Column(name = "update_by")
    private String updateBy;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    private LocalDateTime updateTime;
}

