package com.laughingather.gulimall.admin.entity.to;

import lombok.Data;

/**
 * 用户登录传输类
 *
 * @author laughingather
 */
@Data
public class AdminLoginTO {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

}