package com.laughingather.gulimall.auth.entity.dto;

import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * 管理用户注册传输类
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Data
@ToString
public class AdminRegisterDTO {

    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Length(min = 6, max = 16, message = "密码长度必须在6-16位之间")
    private String password;

    @NotBlank(message = "手机号码不能为空")
    @Pattern(regexp = "^[1]([3-9])[0-9]{9}$", message = "手机号码格式不正确")
    private String mobile;

    @NotBlank(message = "验证码不能为空")
    private String code;

}
