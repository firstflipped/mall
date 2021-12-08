package com.laughingather.gulimall.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author WangJie
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "sys_platform_log")
public class PlatformLog {

    private Long id;

    private Long userId;

    /**
     * 操作用户
     */
    private String username;

    /**
     * URI
     */
    private String uri;

    /**
     * URL
     */
    private String url;

    /**
     * 请求类
     */
    private String className;

    /**
     * 请求方法
     */
    private String methodName;

    /**
     * 请求类型
     */
    private Integer methodType;

    /**
     * 请求参数
     */
    private String methodParams;

    /**
     * 操作描述
     */
    private String methodDescription;

    /**
     * 服务器IP地址
     */
    private String serverIp;

    /**
     * 客户端IP地址
     */
    private String clientIp;

    /**
     * 返回结果，是否成功
     */
    private Integer success;

    /**
     * 消耗时间
     */
    private Long spendTime;

    /**
     * 是否是登录操作
     * 1 表示 是
     * 0 表示 否
     */
    private Integer login;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

}
