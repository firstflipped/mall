package com.flipped.mall.common.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 日志实体
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Data
@TableName(value = "sys_platform_log")
public class PlatformLogEntity {

    private Long logId;

    /**
     * 请求URI
     */
    private String requestUri;

    /**
     * 请求URL
     */
    private String requestUrl;

    /**
     * 请求方法
     */
    private String requestMethod;

    /**
     * 请求参数
     */
    private String requestParams;

    /**
     * 请求类
     */
    private String className;

    /**
     * 请求函数
     */
    private String methodName;

    /**
     * 操作类型
     */
    private Integer methodType;

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
    @TableField(value = "is_success")
    private Integer success;

    /**
     * 是否是登录操作
     * 1 表示 是
     * 0 表示 否
     */
    @TableField(value = "is_login")
    private Integer login;

    /**
     * 消耗时间
     */
    private Long spendTime;

    /**
     * 操作用户id
     */
    private Long operationUserid;

    /**
     * 操作用户
     */
    private String operationUsername;

    /**
     * 创建时间
     */
    private LocalDateTime operationTime;

}
