package com.laughingather.gulimall.auth.feign.entity;

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
public class MemberLoginDTO {

    private String username;
    private String password;

}
