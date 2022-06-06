package com.laughingather.gulimall.auth.feign.service;

import com.laughingather.gulimall.auth.entity.to.MemberLoginTO;
import com.laughingather.gulimall.auth.entity.to.MemberRegisterTO;
import com.laughingather.gulimall.auth.entity.to.SocialUserTO;
import com.laughingather.gulimall.auth.feign.entity.MemberTO;
import com.laughingather.gulimall.common.api.MyResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 会员服务远程调用接口
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@FeignClient("gulimall-member")
public interface MemberFeignService {

    /**
     * 会员远程注册接口
     *
     * @param memberRegisterTO 会员注册传输类
     * @return 注册结果
     */
    @PostMapping("/gulimall-member/openapi/member/register")
    MyResult<Void> register(@RequestBody MemberRegisterTO memberRegisterTO);

    /**
     * 会员远程登录接口
     * 返回的数据就是一个JSON串，只要属性匹配就可以进行转换
     *
     * @param memberLoginTO 会员登录传输类
     * @return 会员信息
     */
    @PostMapping("/gulimall-member/openapi/member/login")
    MyResult<MemberTO> login(@RequestBody MemberLoginTO memberLoginTO);

    /**
     * oauth2登录接口
     *
     * @param socialUserTO
     * @return
     */
    @PostMapping("/gulimall-member/openapi/member/oauth2/login")
    MyResult<MemberTO> oauth2Login(@RequestBody SocialUserTO socialUserTO);

}
