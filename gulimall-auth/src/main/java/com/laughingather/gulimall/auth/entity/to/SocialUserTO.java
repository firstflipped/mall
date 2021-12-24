package com.laughingather.gulimall.auth.entity.to;

import lombok.Data;

/**
 * oauth2返回实体
 *
 * @author：laughingather
 * @create：2021-06-22 22:27
 */
@Data
public class SocialUserTO {

    private String access_token;
    private Long remind_in;
    private Long expires_in;
    private Long uid;
    private Boolean isRealName;

}
