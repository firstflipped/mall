package com.laughingather.gulimall.auth.controller;

import cn.hutool.core.util.RandomUtil;
import com.laughingather.gulimall.auth.entity.dto.UserLoginDTO;
import com.laughingather.gulimall.auth.feign.service.ThirdPartyFeignService;
import com.laughingather.gulimall.auth.service.UserLoginService;
import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.common.constant.AuthConstants;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 登录路由
 *
 * @author：laughingather
 * @create：2021-06-21 23:55
 */
@RestController
public class UserLoginController {

    @Resource
    private ThirdPartyFeignService thirdPartyFeignService;
    @Resource
    private UserLoginService userLoginService;
    @Resource
    private RedisTemplate redisTemplate;

    @GetMapping("/sms/send-code")
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


    @PostMapping("/login")
    public MyResult<String> login(@RequestBody @Valid UserLoginDTO userLoginDTO, BindingResult bindingResult) {
        // 校验参数
        if (bindingResult.hasErrors()) {
            String errors = bindingResult.getFieldErrors().stream().map(FieldError::getDefaultMessage)
                    .collect(Collectors.joining(","));
            return MyResult.errMsg(errors);
        }

        userLoginService.login(userLoginDTO);

        return null;
    }


    @PostMapping("/logout")
    public MyResult logout() {
        return null;
    }


}
