package com.laughingather.gulimall.auth.entity.to;

import lombok.Data;

/**
 * 用户手机号验证登录实体类
 *
 * @author laughingather
 * @version v1.0
 * @since 2022-05-23 15:02:16
 */
@Data
public class AdminLoginByMobileTO {

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 验证码
     */
    private String code;

}

