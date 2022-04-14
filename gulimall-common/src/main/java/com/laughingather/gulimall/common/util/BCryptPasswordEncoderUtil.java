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

    public static String encodingPassword(String password) {
        return bCryptPasswordEncoder.encode(password);
    }

    public static boolean matchesPassword(CharSequence rawPassword, String encodedPassword) {
        return bCryptPasswordEncoder.matches(rawPassword, encodedPassword);
    }

}

