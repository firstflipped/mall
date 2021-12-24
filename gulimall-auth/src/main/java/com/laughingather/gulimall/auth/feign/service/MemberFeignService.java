package com.laughingather.gulimall.auth.feign.service;

import com.laughingather.gulimall.auth.entity.to.MemberLoginTO;
import com.laughingather.gulimall.auth.entity.to.MemberRegisterTO;
import com.laughingather.gulimall.auth.entity.to.SocialUserTO;
import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.common.entity.MemberEntity;
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
     * @param memberRegisterTO
     * @return MyResult
     */
    @PostMapping("/gulimall-member/openapi/member/register")
    MyResult register(@RequestBody MemberRegisterTO memberRegisterTO);

    /**
     * 远程登录接口
     * <p>
     * 返回的数据就是一个JSON串，只要属性匹配就可以进行转换
     *
     * @param memberLoginTO
     * @return
     */
    @PostMapping("/gulimall-member/openapi/member/login")
    MyResult<MemberEntity> login(@RequestBody MemberLoginTO memberLoginTO);

    /**
     * oauth2登录接口
     *
     * @param socialUserTO
     * @return
     */
    @PostMapping("/gulimall-member/openapi/member/oauth2/login")
    MyResult<MemberEntity> oauth2Login(@RequestBody SocialUserTO socialUserTO);

}
