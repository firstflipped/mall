package com.laughingather.gulimall.common.constant;

/**
 * @author WangJie
 */
public class AuthConstants {

    /**
     * 验证码短信前缀
     */
    public static final String SMS_CODE_CACHE_PREFIX = "sms:code:";


    public static final String LOGIN_USER = "loginUser";

    /**
     * 微博oauth2API地址
     */
    public static final String WEIBO_OAUTH_API_URL = "https://api.weibo.com/oauth2/access_token?client_id=%s&client_secret=%s&grant_type=authorization_code&code=%s&redirect_uri=%s";

    /**
     * 获取微博用户信息API地址
     */
    public static final String WEIBO_INFO_API_URL = "https://api.weibo.com/2/users/show.json?access_token=%s&uid=%s";


    /**
     * 生产环境要通过配置文件读取出加密过的key
     */
    public static final String KEY = "flippedmdfsklajfklasjr89e33q4ui90324ut9uqioJKSOPU7897(*)*()*d(^&ERWJHROQIUJIODFJHO9SAHFO0AIKF9";

    /**
     * JWT的签发者
     */
    public static final String ISSUER = "laughingather";

    /**
     * JWT过期时间（24小时）
     */
    public static final int TOKEN_EXP_TIME = 24 * 60 * 60;

    /**
     * 用户名
     */
    public static final String USER_ID = "userId";
}
