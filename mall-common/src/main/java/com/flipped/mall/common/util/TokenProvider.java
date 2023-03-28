package com.flipped.mall.common.util;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.flipped.mall.common.constant.AuthConstants;
import com.flipped.mall.common.entity.JwtPayLoad;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
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
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Slf4j
public class TokenProvider {

    /**
     * 加密方式
     */
    public static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;

    /**
     * 加密钥
     */
    private static final SecretKey SIGNING_KEY;

    /**
     * 密文
     */
    private static byte[] jwtSecret;

    /*
     * 获取配置文件中的密钥
     *
     */
    static {
        try (FileInputStream fis = new FileInputStream("private.key")) {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = fis.read(buffer)) > 0) {
                bos.write(buffer, 0, len);
            }
            jwtSecret = bos.toByteArray();
        } catch (Exception e) {
            log.error("======== 读取系统密文异常，启用自定义密文 =========");
        }
        if (jwtSecret == null) {
            jwtSecret = AuthConstants.KEY.getBytes(StandardCharsets.UTF_8);
        }

        SIGNING_KEY = Keys.hmacShaKeyFor(jwtSecret);
    }


    /**
     * 生成token
     */
    public static String generateToken(JwtPayLoad jwtPayLoad) {

        // token有效期截止时间
        DateTime expirationDate = DateUtil.offsetSecond(new Date(), Convert.toInt(AuthConstants.TOKEN_EXP_TIME));

        return Jwts.builder()
                // 签发者
                .setIssuer(AuthConstants.ISSUER)
                // 消息主题
                .setClaims(BeanUtil.beanToMap(jwtPayLoad))
                .setSubject(jwtPayLoad.getUserid().toString())
                // 签发时间
                .setIssuedAt(new Date())
                // 签发有效截止时间
                .setExpiration(expirationDate)
                .signWith(SIGNING_KEY, SIGNATURE_ALGORITHM)
                .compact();
    }

    /**
     * 根据token获取Claims
     */
    private static Claims getClaimsFromToken(String token) {

        Claims claims = null;

        try {
            claims = Jwts.parserBuilder()
                    .setSigningKey(SIGNING_KEY)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            log.error("获取Claims信息异常：{}", e.getMessage());
        }
        return claims;
    }

    /**
     * 获取JwtPayLoad部分
     */
    public static JwtPayLoad getJwtPayLoad(String token) {
        Claims claims = getClaimsFromToken(token);
        if (claims == null) {
            return null;
        }
        return BeanUtil.toBean(claims, JwtPayLoad.class, null);
    }

    /**
     * 校验token是否正确
     */
    public static Boolean checkToken(String token) {
        try {
            getClaimsFromToken(token);
            return true;
        } catch (ExpiredJwtException ex) {
            log.error("登录凭证已失效", ex);
            return false;
        } catch (Exception ex) {
            log.error("登录凭证不合法", ex);
            return false;
        }
    }

    /**
     * 校验token是否失效
     */
    public static Boolean isTokenExpired(String token) {
        try {
            Claims claims = getClaimsFromToken(token);
            if (claims == null || claims.isEmpty()) {
                return true;
            }
            Date expiration = claims.getExpiration();
            return expiration.before(new Date());
        } catch (ExpiredJwtException expiredJwtException) {
            log.error("登录凭证已失效", expiredJwtException);
            return true;
        }
    }


    /**
     * 获取token有效期（秒为单位）
     *
     * @param token
     * @return
     */
    public static Long getTokenExpire(String token) {
        try {
            Claims claims = getClaimsFromToken(token);
            if (claims == null || claims.isEmpty()) {
                return -1L;
            }
            Date expiration = claims.getExpiration();
            return DateUtil.between(new Date(), expiration, DateUnit.SECOND);
        } catch (ExpiredJwtException expiredJwtException) {
            log.error("登录凭证已失效", expiredJwtException);
            return -1L;
        }
    }


    public static void main(String[] args) {
        JwtPayLoad jwtPayLoad = new JwtPayLoad();
        jwtPayLoad.setUuid(IdUtil.fastUUID());
        jwtPayLoad.setUsername("admin");
        jwtPayLoad.setUserid(111L);
        String token = generateToken(jwtPayLoad);

        log.info("token:{}", token);

        Boolean isToken = checkToken(token);
        log.info("是否是token:{}", isToken);

        JwtPayLoad jwtPayLoadResult = getJwtPayLoad(token);
        log.info("数据是:{}", jwtPayLoadResult);
    }


}
