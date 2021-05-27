package com.laughingather.gulimall.member.entity.dto;

import lombok.Data;
import lombok.ToString;

/**
 * 会员注册远程调用传输类
 *
 * @author：laughingather
 * @create：2021-05-26
 */
@Data
@ToString
public class MemberRegisterDTO {

    private String username;

    private String password;

    private String mobile;

}
