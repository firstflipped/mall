package com.laughingather.gulimall.auth.entity.dto;

import lombok.Data;
import lombok.ToString;

/**
 * 用户登录实体类
 *
 * @author：laughingather
 * @create：2021-05-27 23:03
 */

@Data
@ToString
public class UserLoginDTO {

    private String username;
    private String password;

}
