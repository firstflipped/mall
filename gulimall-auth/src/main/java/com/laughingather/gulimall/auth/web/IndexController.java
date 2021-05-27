package com.laughingather.gulimall.auth.web;

import cn.hutool.core.util.RandomUtil;
import com.laughingather.gulimall.auth.entity.dto.UserLoginDTO;
import com.laughingather.gulimall.auth.entity.dto.UserRegisterDTO;
import com.laughingather.gulimall.auth.feign.entity.MemberLoginDTO;
import com.laughingather.gulimall.auth.feign.entity.MemberRegisterDTO;
import com.laughingather.gulimall.auth.feign.service.MemberFeignService;
import com.laughingather.gulimall.auth.feign.service.ThirdPartyFeignService;
import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.common.api.ResultCodeEnum;
import com.laughingather.gulimall.common.constant.AuthConstants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author WangJie
 */
@Controller
public class IndexController {

    @Resource
    private ThirdPartyFeignService thirdPartyFeignService;
    @Resource
    private MemberFeignService memberFeignService;
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


    /**
     * RedirectAttributes  重定向携带数据
     *
     * @param userRegisterDTO
     * @param bindingResult
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/register")
    public String register(@Valid UserRegisterDTO userRegisterDTO,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            Map<String, String> errors = bindingResult.getFieldErrors()
                    .stream().collect(Collectors.toMap(
                            FieldError::getField,
                            FieldError::getDefaultMessage
                    ));

            redirectAttributes.addFlashAttribute("errors", errors);

            // 默认映射页面都是GET请求，所以转发会报错
            // return "forward:/register.html";

            return "redirect:http://auth.gulimall.com/register.html";
        }

        // 调用远程服务进行注册
        String cacheCode = redisTemplate.opsForValue().get(AuthConstants.SMS_CODE_CACHE_PREFIX + userRegisterDTO.getMobile()).toString();
        if (StringUtils.isBlank(cacheCode)) {
            Map<String, String> errors = new HashMap<>(2);
            errors.put("code", "验证码过期");
            redirectAttributes.addFlashAttribute("errors", errors);
            return "redirect:http://auth.gulimall.com/register.html";
        } else {
            if (!cacheCode.equalsIgnoreCase(userRegisterDTO.getCode())) {
                Map<String, String> errors = new HashMap<>(2);
                errors.put("code", "验证码错误");
                redirectAttributes.addFlashAttribute("errors", errors);
                return "redirect:http://auth.gulimall.com/register.html";
            }
        }

        // 执行正确的逻辑（先删除缓存中的验证码）
        redisTemplate.delete(AuthConstants.SMS_CODE_CACHE_PREFIX + userRegisterDTO.getMobile());

        MemberRegisterDTO memberRegisterDTO = new MemberRegisterDTO();
        BeanUtils.copyProperties(userRegisterDTO, memberRegisterDTO);
        MyResult result = memberFeignService.memberRegister(memberRegisterDTO);
        if (result.getCode().equals(ResultCodeEnum.SUCCESS.getCode())) {
            return "redirect:http://auth.gulimall.com/login.html";
        } else {
            Map<String, String> errors = new HashMap<>(2);
            errors.put("message", result.getMessage());
            redirectAttributes.addFlashAttribute("errors", errors);
            return "redirect:http://auth.gulimall.com/register.html";
        }
    }


    @PostMapping("/login")
    public String login(UserLoginDTO userLoginDTO, RedirectAttributes redirectAttributes) {
        MemberLoginDTO memberLoginDTO = new MemberLoginDTO();
        BeanUtils.copyProperties(userLoginDTO, memberLoginDTO);

        MyResult result = memberFeignService.memberLogin(memberLoginDTO);
        if (result.getCode().equals(ResultCodeEnum.SUCCESS.getCode())) {
            return "redirect:http://gulimall.com";
        }

        // 错误返回
        HashMap<String, String> errors = new HashMap<>(2);
        errors.put("message", result.getMessage());
        redirectAttributes.addFlashAttribute("errors", errors);
        return "redirect:http://auth.gulimall.com/login.html";
    }

}
