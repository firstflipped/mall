package com.laughingather.gulimall.auth.entity.to;

import lombok.Data;

/**
 * oauth2返回实体
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Data
public class SocialUserTO {

    private String access_token;
    private Long remind_in;
    private Long expires_in;
    private Long uid;
    private Boolean isRealName;

}
