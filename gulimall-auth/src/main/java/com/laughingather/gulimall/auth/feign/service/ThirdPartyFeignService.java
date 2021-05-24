package com.laughingather.gulimall.auth.feign.service;

import com.laughingather.gulimall.common.api.MyResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author WangJie
 */

@FeignClient("gulimall-third-party")
public interface ThirdPartyFeignService {

    /**
     * 第三方发送短信接口
     *
     * @param phoneNumber
     * @param code
     * @return
     */
    @GetMapping("/gulimall-third-party/sms/sms-send.do")
    MyResult sendCheckCode(@RequestParam(name = "phoneNumber") String phoneNumber,
                           @RequestParam(name = "code") String code);

}
