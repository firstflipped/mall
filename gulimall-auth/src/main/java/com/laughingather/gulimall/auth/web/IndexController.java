package com.laughingather.gulimall.auth.web;

import cn.hutool.core.util.RandomUtil;
import com.laughingather.gulimall.auth.feign.entity.UserRegisterDTO;
import com.laughingather.gulimall.auth.feign.service.ThirdPartyFeignService;
import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.common.constant.AuthConstants;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author WangJie
 */
@Controller
public class IndexController {

    @Resource
    private ThirdPartyFeignService thirdPartyFeignService;
    @Resource
    private RedisTemplate redisTemplate;

    @GetMapping("/sms/send-code")
    @ResponseBody
    public MyResult sendCode(@RequestParam("phoneNum") String phoneNum) {
        Object cacheCode = redisTemplate.opsForValue().get(AuthConstants.SMS_CODE_CACHE_PREFIX + phoneNum);
        if (!Objects.isNull(cacheCode)) {
            // 如果缓存中存在验证码则不允许重发
            return MyResult.failed();
        }

        // 生成随机验证码
        String code = RandomUtil.randomNumbers(6);
        // 把验证码放到缓存中
        redisTemplate.opsForValue().set(AuthConstants.SMS_CODE_CACHE_PREFIX + phoneNum, code, 60, TimeUnit.SECONDS);

        // TODO：第三方调用短信服务暂不可用
        thirdPartyFeignService.sendCheckCode(phoneNum, code);

        return MyResult.success();
    }


    @PostMapping("/register")
    public String register(@RequestBody @Valid UserRegisterDTO userRegisterDTO,
                           BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "forward:/register.html";
        }

        // 注册成功跳转到登录页
        return "redirect:/login.html";
    }


}
