package com.laughingather.gulimall.member.entity.to;

import lombok.Data;
import lombok.ToString;

/**
 * 用户登录实体类
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Data
@ToString
public class MemberLoginTO {

    /**
     * 会员名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

}
