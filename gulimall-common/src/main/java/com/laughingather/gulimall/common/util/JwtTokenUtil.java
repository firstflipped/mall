package com.laughingather.gulimall.common.util;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.laughingather.gulimall.common.constant.AuthConstants;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.SecretKey;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * JwtToken工具类
 *
 * @author xuyuxiang
 * @date 2020/3/12 17:39
 */
@Slf4j
public class JwtTokenUtil {

    private static byte[] jwtSecret = null;
    private static SecretKey signingKey = null;
    private static final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;
    private static final String BEARER_PREFIX = "Bearer ";

    static {
        try {
            FileInputStream fis = new FileInputStream("private.key");
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = fis.read(buffer)) > 0) {
                bos.write(buffer, 0, len);
            }
            jwtSecret = bos.toByteArray();
        } catch (Exception e) {
            log.error("读取配置文件异常");
        }
        if (jwtSecret == null) {
            jwtSecret = "3XKfzFReDSSipqnmYYbXNt6X9GBq83zzuW8N77sOtlGr8aLp0IxbYABRgU7HSNSr".getBytes(StandardCharsets.UTF_8);
        }

        signingKey = Keys.hmacShaKeyFor(jwtSecret);
    }


    /**
     * 生成token
     *
     * @author xuyuxiang
     * @date 2020/3/12 17:52
     */
    public static String generateToken(JwtPayLoad jwtPayLoad) {

        // token有效期截止时间
        DateTime expirationDate = DateUtil.offsetSecond(new Date(), Convert.toInt(AuthConstants.TOKEN_EXP_TIME));

        return Jwts.builder()
                .setClaims(BeanUtil.beanToMap(jwtPayLoad))
                .setSubject(jwtPayLoad.getUserId().toString())
                .setIssuedAt(new Date())
                .setExpiration(expirationDate)
                .signWith(signingKey, signatureAlgorithm)
                .compact();
    }

    /**
     * 根据token获取Claims
     *
     * @author xuyuxiang
     * @date 2020/3/13 10:29
     */
    private static Claims getClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(signingKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 获取JwtPayLoad部分
     *
     * @author xuyuxiang
     * @date 2020/3/12 17:53
     */
    public static JwtPayLoad getJwtPayLoad(String token) {
        Claims claims = getClaimsFromToken(token);
        return BeanUtil.mapToBean(claims, JwtPayLoad.class, false);
    }

    /**
     * 校验token是否正确
     *
     * @author xuyuxiang
     * @date 2020/3/13 10:36
     */
    public static Boolean checkToken(String token) {
        try {
            getClaimsFromToken(token);
            return true;
        } catch (Exception ex) {
            log.error("登录凭证不合法", ex);
            return false;
        }
    }

    /**
     * 校验token是否失效
     *
     * @author xuyuxiang
     * @date 2020/3/13 10:30
     */
    public static Boolean isTokenExpired(String token) {
        try {
            Claims claims = getClaimsFromToken(token);
            final Date expiration = claims.getExpiration();
            return expiration.before(new Date());
        } catch (ExpiredJwtException expiredJwtException) {
            return true;
        }
    }


    public static void main(String[] args) {
        JwtPayLoad jwtPayLoad = new JwtPayLoad();
        jwtPayLoad.setUuid(IdUtil.fastUUID());
        jwtPayLoad.setAccount("admin");
        jwtPayLoad.setUserId(111L);
        String token = generateToken(jwtPayLoad);
        log.info("token:{}", token);

        Boolean isToken = checkToken(token);
        log.info("是否是token:{}", isToken);

        JwtPayLoad jwtPayLoadResult = getJwtPayLoad(token);
        log.info("数据是:{}", jwtPayLoadResult);
    }


}
