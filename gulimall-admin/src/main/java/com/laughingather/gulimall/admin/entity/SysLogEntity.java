package com.laughingather.gulimall.admin.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * 日志实体
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Data
public class SysLogEntity {
    /**
     *
     */
    private Long id;

    /**
     * 日志类型（1登录日志，2操作日志）
     */
    private Boolean logType;

    /**
     * 日志内容
     */
    private String logContent;

    /**
     * 操作类型
     */
    private Integer operateType;

    /**
     * 操作用户账号
     */
    private Long userId;

    /**
     * 操作用户名称
     */
    private String username;

    /**
     * IP
     */
    private Integer ip;

    /**
     * 请求java方法
     */
    private String method;

    /**
     * 请求路径
     */
    private String requestUrl;

    /**
     * 请求参数
     */
    private String requestParam;

    /**
     * 请求类型
     */
    private String requestType;

    /**
     * 耗时
     */
    private Long costTime;

    /**
     * 创建人
     */
    private String createBy;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 更新人
     */
    private String updateBy;

    /**
     * 更新时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}

