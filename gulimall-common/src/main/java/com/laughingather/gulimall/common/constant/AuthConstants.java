package com.laughingather.gulimall.common.constant;

/**
 * 权限服务常量
 *
 * @author：laughingather
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
    public static final String KEY = "3XKfzFReDSSipAq6543nmYYbXNt6X9GBq83zzuW8N77sOtlGr8aLp0IxbYABRgU7HSNSr(*)*()*d(^&X9GB8945892W8N77sOtlG";

    /**
     * JWT的签发者
     */
    public static final String ISSUER = "laughingather";

    /**
     * JWT过期时间（24小时）
     */
    public static final int TOKEN_EXP_TIME = 24 * 60 * 60;


    /**
     * token前缀
     */
    public static final String TOKEN_PREFIX = "Bearer ";

    /**
     * 用户id
     * 用户姓名
     */
    public static final String USERID = "userid";
    public static final String USERNAME = "username";
}
