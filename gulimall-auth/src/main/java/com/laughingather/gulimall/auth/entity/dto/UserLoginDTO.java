package com.laughingather.gulimall.auth.entity.dto;

import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * 用户登录实体类
 *
 * @author：laughingather
 * @create：2021-05-27 23:03
 */

@Data
@ToString
public class UserLoginDTO {

    @NotBlank(message = "用户名必须填写")
    private String username;

    @NotBlank(message = "密码必须填写")
    @Length(min = 6, max = 16, message = "密码长度必须在6-16位之间")
    private String password;

}
