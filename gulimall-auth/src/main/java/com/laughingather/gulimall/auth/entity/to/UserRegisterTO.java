package com.laughingather.gulimall.auth.entity.to;

import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * 管理用户注册传输类
 *
 * @author：laughingather
 */
@Data
@ToString
public class UserRegisterTO {

    @NotBlank(message = "用户名必须填写")
    private String username;

    @NotBlank(message = "密码必须填写")
    @Length(min = 6, max = 16, message = "密码长度必须在6-16位之间")
    private String password;

    @NotBlank(message = "手机号必须填写")
    @Pattern(regexp = "^[1]([3-9])[0-9]{9}$", message = "手机格式不正确")
    private String mobile;

    @NotBlank(message = "验证码必须填写")
    private String code;

}
