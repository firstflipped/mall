package com.laughingather.gulimall.common.util;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.laughingather.gulimall.common.constant.AuthConstants;
import com.laughingather.gulimall.common.entity.JwtPayLoad;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.SecretKey;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * JwtToken工具类
 *
 * @author：laughingather
 * @date 2022-01-12
 */
@Slf4j
public class TokenProvider {

    /**
     * 密文
     */
    private static byte[] jwtSecret;

    /**
     * 加密钥
     */
    private static final SecretKey SIGNING_KEY;

    /**
     * 加密方式
     */
    public static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;

    /**
     * 获取配置文件中的密钥
     *
     */
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
            log.error("读取密钥异常");
        }
        if (jwtSecret == null) {
            jwtSecret = AuthConstants.KEY.getBytes(StandardCharsets.UTF_8);
        }

        SIGNING_KEY = Keys.hmacShaKeyFor(jwtSecret);
    }


    /**
     * 生成token
     *
     */
    public static String generateToken(JwtPayLoad jwtPayLoad) {

        // token有效期截止时间
        DateTime expirationDate = DateUtil.offsetSecond(new Date(), Convert.toInt(AuthConstants.TOKEN_EXP_TIME));

        String token = Jwts.builder()
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

        return AuthConstants.TOKEN_PREFIX + token;
    }

    /**
     * 根据token获取Claims
     *
     */
    private static Claims getClaimsFromToken(String token) {
        if (StringUtils.isBlank(token)) {
            return null;
        }

        return Jwts.parserBuilder()
                .setSigningKey(SIGNING_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 获取JwtPayLoad部分
     *
     */
    public static JwtPayLoad getJwtPayLoad(String token) {
        Claims claims = getClaimsFromToken(token);
        return BeanUtil.mapToBean(claims, JwtPayLoad.class, false);
    }

    /**
     * 校验token是否正确
     *
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
