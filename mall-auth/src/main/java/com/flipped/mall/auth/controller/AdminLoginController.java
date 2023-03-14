package com.flipped.mall.auth.controller;

import cn.hutool.core.util.RandomUtil;
import com.flipped.mall.auth.entity.dto.AdminLoginByMobileDTO;
import com.flipped.mall.auth.entity.dto.AdminLoginDTO;
import com.flipped.mall.auth.entity.vo.TokenVO;
import com.flipped.mall.auth.feign.entity.AdminInfoDTO;
import com.flipped.mall.auth.feign.entity.AdminPermissionDTO;
import com.flipped.mall.auth.feign.service.ThirdPartyFeignService;
import com.flipped.mall.auth.service.AdminLoginService;
import com.flipped.mall.common.constant.AuthConstants;
import com.flipped.mall.common.entity.api.ErrorCodeEnum;
import com.flipped.mall.common.entity.api.MyResult;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
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


    @DeleteMapping("/logout")
    public MyResult<Void> logout(HttpServletRequest request) {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);

        // TODO: 退出登录功能待实现
        adminLoginService.logout(token);
        return MyResult.success();
    }


    @GetMapping("/userinfo")
    public MyResult<AdminInfoDTO> getUserinfo(HttpServletRequest request) {
        // 获取请求头里面的token信息
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);

        AdminInfoDTO adminInfoDTO = adminLoginService.getUserinfo(token);
        return MyResult.success(adminInfoDTO);
    }


    @GetMapping("/permission")
    public MyResult<List<AdminPermissionDTO>> getPermission(HttpServletRequest request) {
        // 获取请求头里面的token信息
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);

        List<AdminPermissionDTO> adminPermissionDTOList = adminLoginService.getPermission(token);
        return MyResult.success(adminPermissionDTOList);
    }


    @PostMapping("/refresh")
    public MyResult<TokenVO> refreshToken() {
        // TODO: 刷新token功能待实现
        return null;
    }


}
