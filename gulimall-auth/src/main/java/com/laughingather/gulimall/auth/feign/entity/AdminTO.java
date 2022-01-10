package com.laughingather.gulimall.auth.feign.entity;

import lombok.Data;

/**
 * 用户信息传输类
 *
 * @author：laughingather
 * @create：2021-12-06 2021/12/6
 */
@Data
public class AdminTO {
    private Long userid;

    /**
     * 登录账号
     */
    private String username;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 电话
     */
    private String phone;

}

