package com.laughingather.gulimall.auth.controller;

import com.laughingather.gulimall.auth.entity.SocialUser;
import com.laughingather.gulimall.auth.feign.entity.MemberEntity;
import com.laughingather.gulimall.auth.feign.service.MemberFeignService;
import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.common.api.ResultCodeEnum;
import com.laughingather.gulimall.common.constant.AuthConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * oauth2.0
 *
 * @author：laughingather
 * @create：2021-06-21 23:57
 */

@Slf4j
@Controller
@RequestMapping("/oauth2.0")
public class OAuth2Controller {

    @Resource
    private RestTemplate restTemplate;
    @Resource
    private MemberFeignService memberFeignService;

    @Value("${weibo.app-key}")
    private String appKey;
    @Value("${weibo.app-secret}")
    private String appSecret;
    @Value("${weibo.callback-url}")
    private String callbackUrl;

    @GetMapping("/weibo/success")
    public String getAccessToken(HttpSession session, HttpServletResponse response, @RequestParam("code") String code) {
        String sendUrl = String.format(AuthConstants.WEIBO_OAUTH_API_URL, appKey, appSecret, code, callbackUrl);
        log.info("请求获取凭证信息地址{}", sendUrl);
        ResponseEntity<SocialUser> result = restTemplate.postForEntity(sendUrl, null, SocialUser.class);
        // 成功
        if (result.getStatusCode() == HttpStatus.OK) {
            // 获取到accessToken
            SocialUser socialUser = result.getBody();
            log.info("获取到的凭证信息{}", socialUser);

            // 当前用户如果是第一次登陆此网址，则自动进行用户注册
            MyResult<MemberEntity> myResult = memberFeignService.oauth2Login(socialUser);
            if (Objects.equals(ResultCodeEnum.SUCCESS.getCode(), myResult.getCode())) {
                MemberEntity data = myResult.getData();
                log.info("用户名：{}", data.getNickname());
                session.setAttribute("loginUser", data);
                return "redirect:http://gulimall.com";
            }
        }

        // 重定向到登录页（失败）
        return "redirect:http://auth.gulimall.com/login.html";
    }

}
