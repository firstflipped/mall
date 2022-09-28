package com.laughingather.gulimall.auth.controller;

import cn.hutool.core.util.RandomUtil;
import com.laughingather.gulimall.auth.entity.dto.AdminLoginByMobileDTO;
import com.laughingather.gulimall.auth.entity.dto.AdminLoginDTO;
import com.laughingather.gulimall.auth.entity.vo.AdminVO;
import com.laughingather.gulimall.auth.entity.vo.TokenVO;
import com.laughingather.gulimall.auth.feign.service.ThirdPartyFeignService;
import com.laughingather.gulimall.auth.service.AdminLoginService;
import com.laughingather.gulimall.common.api.ErrorCodeEnum;
import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.common.constant.AuthConstants;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.concurrent.TimeUnit;

/**
 * 管理用户登录路由
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@RestController
@RequestMapping("/auth/admin")
public class AdminLoginController {

    @Resource
    private ThirdPartyFeignService thirdPartyFeignService;
    @Resource
    private AdminLoginService adminLoginService;
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @GetMapping("/sms/send-code")
    public MyResult<Void> sendSmsCode(@RequestParam("mobile") String mobile) {
        Boolean hasSmsCode = redisTemplate.hasKey(AuthConstants.SMS_CODE_CACHE_PREFIX + mobile);
        if (Boolean.TRUE.equals(hasSmsCode)) {
            // 如果缓存中存在验证码则不允许重发
            return MyResult.failed();
        }

        // 生成随机验证码
        String code = RandomUtil.randomNumbers(6);

        // TODO：第三方调用短信服务暂不可用
        thirdPartyFeignService.sendCheckCode(mobile, code);

        // 把验证码放到缓存中
        redisTemplate.opsForValue().set(AuthConstants.SMS_CODE_CACHE_PREFIX + mobile, code, 5, TimeUnit.SECONDS);

        return MyResult.success();
    }

    @PostMapping("/login")
    public MyResult<TokenVO> login(@Valid @RequestBody AdminLoginDTO adminLoginDTO) {
        // TODO: 还有优化空间
        TokenVO tokenVO = adminLoginService.login(adminLoginDTO);
        return tokenVO != null ? MyResult.success(tokenVO) : MyResult.failed(ErrorCodeEnum.ACCOUNT_PASSWORD_INVALID_EXCEPTION);
    }

    @PostMapping("/login/mobile")
    public MyResult<TokenVO> loginByMobile(@Valid @RequestBody AdminLoginByMobileDTO adminLoginByMobileDTO) {
        TokenVO tokenVO = adminLoginService.loginByMobile(adminLoginByMobileDTO);
        return tokenVO != null ? MyResult.success(tokenVO) : MyResult.failed(ErrorCodeEnum.MOBILE_LOGIN_EXCEPTION);
    }


    @PostMapping("/logout")
    public MyResult<Void> logout() {
        // TODO: 退出登录功能待实现
        return null;
    }


    @GetMapping("/userinfo")
    public MyResult<AdminVO> getUserinfo(@RequestParam("token") String token) {
        AdminVO adminVO = adminLoginService.getUserinfo(token);
        return MyResult.success(adminVO);
    }


}
