package com.laughingather.gulimall.auth.controller;

import com.laughingather.gulimall.common.api.MyResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Map;

/**
 * oauth2.0
 *
 * @author：laughingather
 * @create：2021-06-21 23:57
 */

@Slf4j
@RestController
@RequestMapping("/oauth2.0")
public class OAuth2Controller {

    @Resource
    private RestTemplate restTemplate;

    @Value("${weibo.app-key}")
    private String appKey;
    @Value("${weibo.app-secret}")
    private String appSecret;
    @Value("${weibo.callback-url}")
    private String callbackUrl;

    @GetMapping("/weibo/success")
    public MyResult<String> getAccessToken(@RequestParam("code") String code) {
        final String url = "https://api.weibo.com/oauth2/access_token?client_id=%s&client_secret=%s&grant_type=authorization_code&code=%s&redirect_uri=%s";
        String sendUrl = String.format(url, appKey, appSecret, code, callbackUrl);
        log.info("请求地址{}", sendUrl);
        ResponseEntity<Map> result = restTemplate.postForEntity(sendUrl, null, Map.class);
        log.info("{}", result.getBody());
        return null;
    }

}
