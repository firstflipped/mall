package com.flipped.mall.member;

import com.flipped.mall.common.constant.AuthConstants;
import com.flipped.mall.member.entity.dto.WeiboUserInfo;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.Md5Crypt;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;

@SpringBootTest
class MallMemberApplicationTests {

    @Resource
    private RestTemplate restTemplate;

    /**
     * 最终方案
     */
    @Test
    public void passwordMd5() {
        // 容易被彩虹表暴力破解
        String s = DigestUtils.md5Hex("123456");

        // e10adc3949ba59abbe56e057f20f883e
        System.out.println(s);


        // 加盐解决（随机盐），但是我们需要知道盐值来比对数据库的密码，所以需要数据库中有一个字段专门存储盐值
        // 盐值一样的情况下，加密出来的密文也是一样的
        String s1 = Md5Crypt.md5Crypt("123456".getBytes(StandardCharsets.UTF_8));
        // $1$DHAbF7lb$3g5WGSLaTp3pCtRbbpniS/
        // $1$9gdzowYX$61yV6YgHyZOm089IbUoE21
        System.out.println(s1);
    }


    /**
     * 最终方案
     */
    @Test
    public void password() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode("123456");

        // $2a$10$/8cnGU44i1bcvwT6s4Vh3ux73nJ6lO.RdNQPnfs.ARGhSyG6gMF8a
        // $2a$10$Fz7sNI6ngceGYNvx9YuHj.R9s62W6RI2BPQWe6eo79o8WGCUpJI0a
        System.out.println(encode);

        boolean matches1 = bCryptPasswordEncoder.matches("123456", "$2a$10$/8cnGU44i1bcvwT6s4Vh3ux73nJ6lO.RdNQPnfs.ARGhSyG6gMF8a");
        boolean matches2 = bCryptPasswordEncoder.matches("123456", "$2a$10$Fz7sNI6ngceGYNvx9YuHj.R9s62W6RI2BPQWe6eo79o8WGCUpJI0a");

        System.out.println(matches1);
        System.out.println(matches2);

    }


    /**
     * 通过accessToken获取微博信息
     */
    @Test
    public void weiboInfo() {
        String sendUrl = String.format(AuthConstants.WEIBO_INFO_API_URL, "2.00Qu2VOGppkbeC8f7ac62209LyPwxD", "5711138076");
        ResponseEntity<WeiboUserInfo> result = restTemplate.getForEntity(sendUrl, WeiboUserInfo.class);
        if (result.getStatusCode() == HttpStatus.OK) {
            WeiboUserInfo weiboUserInfo = result.getBody();
            System.out.println(weiboUserInfo);
        }

    }

}
