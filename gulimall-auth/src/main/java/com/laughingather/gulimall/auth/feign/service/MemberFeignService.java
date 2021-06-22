package com.laughingather.gulimall.auth.feign.service;

import com.laughingather.gulimall.auth.entity.SocialUser;
import com.laughingather.gulimall.auth.feign.entity.MemberEntity;
import com.laughingather.gulimall.auth.feign.entity.MemberLoginDTO;
import com.laughingather.gulimall.auth.feign.entity.MemberRegisterDTO;
import com.laughingather.gulimall.common.api.MyResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 会员服务远程调用接口
 *
 * @author：laughingather
 * @create：2021-05-26 22:53
 */
@FeignClient("gulimall-member")
public interface MemberFeignService {

    /**
     * 远程注册接口
     *
     * @param memberRegisterDTO
     * @return MyResult
     */
    @PostMapping("/gulimall-member/openapi/member/register")
    MyResult memberRegister(@RequestBody MemberRegisterDTO memberRegisterDTO);

    /**
     * 远程登录接口
     *
     * @param memberLoginDTO
     * @return
     */
    @PostMapping("/gulimall-member/openapi/member/login")
    MyResult memberLogin(@RequestBody MemberLoginDTO memberLoginDTO);

    /**
     * oauth2登录接口
     *
     * @param socialUser
     * @return
     */
    @PostMapping("/gulimall-member/openapi/member/oauth2/login")
    MyResult<MemberEntity> oauth2Login(@RequestBody SocialUser socialUser);

}
