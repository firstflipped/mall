package com.laughingather.gulimall.thirdparty.openapi;

import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.thirdparty.service.MySmsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 第三方服务对外开放接口
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@RestController
@RequestMapping("/openapi/third-party")
public class ThirdPartyOpenApi {

    @Resource
    private MySmsService mySmsService;

    @GetMapping("/sms/sms-send.do")
    MyResult<Void> sendCheckCode(@RequestParam(name = "mobile") String mobile,
                                 @RequestParam(name = "code") String code) {
        mySmsService.sendCheckCode(mobile, code);
        return MyResult.success();
    }

}

