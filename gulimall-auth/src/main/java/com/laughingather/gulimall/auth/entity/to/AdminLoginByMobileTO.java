package com.laughingather.gulimall.auth.entity.to;

import lombok.Data;

import javax.validation.constraints.NotBlank;

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
    @NotBlank(message = "手机号码不能为空")
    private String mobile;

    /**
     * 验证码
     */
    @NotBlank(message = "验证码不能为空")
    private String code;

}

