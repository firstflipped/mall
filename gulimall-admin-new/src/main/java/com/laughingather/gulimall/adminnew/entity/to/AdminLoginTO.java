package com.laughingather.gulimall.adminnew.entity.to;

import lombok.Data;

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