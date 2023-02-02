package com.laughingather.gulimall.auth.feign.service;

import com.laughingather.gulimall.common.entity.api.MyResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 第三方服务远程调用接口
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@FeignClient("gulimall-third-party")
public interface ThirdPartyFeignService {

    /**
     * 第三方发送短信接口
     *
     * @param mobile 手机号码
     * @param code   验证码
     * @return Void
     */
    @GetMapping("/gulimall-third-party/openapi/third-party/sms/sms-send.do")
    MyResult<Void> sendCheckCode(@RequestParam(name = "mobile") String mobile,
                                 @RequestParam(name = "code") String code);

}
