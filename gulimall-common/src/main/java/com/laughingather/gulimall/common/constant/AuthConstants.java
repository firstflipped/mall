package com.laughingather.gulimall.common.constant;

/**
 * @author WangJie
 */
public class AuthConstants {

    public static final String SMS_CODE_CACHE_PREFIX = "sms:code:";

    public static final String LOGIN_USER = "loginUser";

    public static final String WEIBO_OAUTH_API_URL = "https://api.weibo.com/oauth2/access_token?client_id=%s&client_secret=%s&grant_type=authorization_code&code=%s&redirect_uri=%s";

    public static final String WEIBO_INFO_API_URL = "https://api.weibo.com/2/users/show.json?access_token=%s&uid=%s";

}
