package com.flipped.mall.auth.entity.dto;

import lombok.Data;

/**
 * oauth2返回实体
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Data
public class SocialUserDTO {

    /**
     * access_token
     */
    private String access_token;


    private Long remind_in;

    /**
     * 过期时间
     */
    private Long expires_in;

    private Long uid;

    private Boolean isRealName;

}
