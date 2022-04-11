package com.laughingather.gulimall.member.entity.dto;

import lombok.Data;
import lombok.ToString;

/**
 * 会员注册远程调用传输类
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Data
@ToString
public class MemberRegisterDTO {

    private String username;

    private String password;

    private String mobile;

}
