package com.laughingather.gulimall.common.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 密码加密类
 *
 * @author laughingather
 * @version v1.0
 * @since 2022-04-13 10:51:25
 */
public class BCryptPasswordEncoderUtil {

    private static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    /**
     * 密码加密
     *
     * @param password 传入密码
     * @return 加密后的密码
     */
    public static String encodingPassword(String password) {
        return bCryptPasswordEncoder.encode(password);
    }

    /**
     * 密码校验
     *
     * @param rawPassword     传入密码
     * @param encodedPassword 数据库中查询出来的密码（加密后的）
     * @return 检验结果
     */
    public static boolean matchesPassword(CharSequence rawPassword, String encodedPassword) {
        return bCryptPasswordEncoder.matches(rawPassword, encodedPassword);
    }

}
