package com.laughingather.gulimall.auth.web;

import com.laughingather.gulimall.auth.entity.to.MemberLoginDTO;
import com.laughingather.gulimall.auth.entity.to.MemberRegisterDTO;
import com.laughingather.gulimall.auth.feign.service.MemberFeignService;
import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.common.api.ResultCodeEnum;
import com.laughingather.gulimall.common.constant.AuthConstants;
import com.laughingather.gulimall.common.entity.MemberEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author WangJie
 */
@Controller
public class IndexController {

    @Resource
    private MemberFeignService memberFeignService;
    @Resource
    private RedisTemplate redisTemplate;


    /**
     * RedirectAttributes  重定向携带数据
     *
     * @param memberRegisterDTO
     * @param bindingResult
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/register")
    public String register(@Valid MemberRegisterDTO memberRegisterDTO,
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
        String cacheCode = redisTemplate.opsForValue().get(AuthConstants.SMS_CODE_CACHE_PREFIX + memberRegisterDTO.getMobile()).toString();
        if (StringUtils.isBlank(cacheCode)) {
            Map<String, String> errors = new HashMap<>(2);
            errors.put("code", "验证码过期");
            redirectAttributes.addFlashAttribute("errors", errors);
            return "redirect:http://auth.gulimall.com/register.html";
        } else {
            if (!cacheCode.equalsIgnoreCase(memberRegisterDTO.getCode())) {
                Map<String, String> errors = new HashMap<>(2);
                errors.put("code", "验证码错误");
                redirectAttributes.addFlashAttribute("errors", errors);
                return "redirect:http://auth.gulimall.com/register.html";
            }
        }

        // 执行正确的逻辑（先删除缓存中的验证码）
        redisTemplate.delete(AuthConstants.SMS_CODE_CACHE_PREFIX + memberRegisterDTO.getMobile());


        MyResult result = memberFeignService.register(memberRegisterDTO);
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
    public String login(HttpSession session, RedirectAttributes redirectAttributes, MemberLoginDTO memberLoginDTO) {

        MyResult<MemberEntity> result = memberFeignService.login(memberLoginDTO);
        if (result.getCode().equals(ResultCodeEnum.SUCCESS.getCode())) {
            session.setAttribute(AuthConstants.LOGIN_USER, result.getData());
            return "redirect:http://gulimall.com";
        }

        // 错误返回
        HashMap<String, String> errors = new HashMap<>(2);
        errors.put("message", result.getMessage());
        redirectAttributes.addFlashAttribute("errors", errors);
        return "redirect:http://auth.gulimall.com/login.html";
    }

    @GetMapping("/login.html")
    public String loginPage(HttpSession session) {
        Object loginUser = session.getAttribute(AuthConstants.LOGIN_USER);
        if (Objects.isNull(loginUser)) {
            return "login";
        }

        return "redirect:http://gulimall.com";
    }

}
