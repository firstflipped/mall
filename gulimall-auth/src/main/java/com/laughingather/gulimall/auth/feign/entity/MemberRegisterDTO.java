package com.laughingather.gulimall.auth.feign.entity;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MemberRegisterDTO {

    private String username;

    private String password;

    private String mobile;

}