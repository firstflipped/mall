package com.laughingather.gulimall.member.openapi;

import com.laughingather.gulimall.common.api.ErrorCodeEnum;
import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.member.entity.MemberEntity;
import com.laughingather.gulimall.member.entity.MemberReceiveAddressEntity;
import com.laughingather.gulimall.member.entity.to.*;
import com.laughingather.gulimall.member.exception.MobileExistException;
import com.laughingather.gulimall.member.exception.UsernameExistException;
import com.laughingather.gulimall.member.service.MemberReceiveAddressService;
import com.laughingather.gulimall.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 会员服务对外提供接口
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Slf4j
@RestController
@RequestMapping("/openapi/member")
public class MemberOpenApi {

    @Resource
    private MemberService memberService;
    @Resource
    private MemberReceiveAddressService memberReceiveAddressService;

    /**
     * 用户注册
     *
     * @param memberRegisterTO
     * @return
     */
    @PostMapping("/register")
    public MyResult<Void> register(@RequestBody MemberRegisterTO memberRegisterTO) {
        try {
            memberService.registerMember(memberRegisterTO);
        } catch (UsernameExistException e) {
            return MyResult.failed(ErrorCodeEnum.USERNAME_EXIST_EXCEPTION);
        } catch (MobileExistException e) {
            return MyResult.failed(ErrorCodeEnum.MOBILE_EXIST_EXCEPTION);
        }

        return MyResult.success();
    }


    /**
     * 用户登录
     *
     * @param memberLoginTO
     * @return
     */
    @PostMapping("/login")
    public MyResult<MemberTO> login(@RequestBody MemberLoginTO memberLoginTO) {
        MemberTO member = memberService.checkLogin(memberLoginTO);
        return member == null ? MyResult.failed(ErrorCodeEnum.ACCOUNT_PASSWORD_INVALID_EXCEPTION) : MyResult.success(member);
    }


    /**
     * oauth2方式登录
     *
     * @param socialUser
     * @return
     */
    @PostMapping("/oauth2/login")
    public MyResult<MemberTO> oauth2Login(@RequestBody SocialUser socialUser) {
        MemberTO member = memberService.login(socialUser);
        return member == null ? MyResult.failed(ErrorCodeEnum.OAUTH_LOGIN_EXCEPTION) : MyResult.success(member);
    }


    /**
     * 获取会员信息
     *
     * @param memberId 会员id
     * @return 会员信息
     */
    @GetMapping("/{mid}/info")
    public MyResult<MemberTO> getMember(@PathVariable("mid") Long memberId) {
        MemberEntity member = memberService.getById(memberId);

        MemberTO memberTO = new MemberTO();
        BeanUtils.copyProperties(member, memberTO);
        return MyResult.success(memberTO);
    }


    /**
     * 获取会员收货地址列表
     *
     * @param memberId 会员id
     * @return
     */
    @GetMapping("/{mid}/address")
    public MyResult<List<MemberReceiveAddressTO>> listMemberReceiveAddresses(@PathVariable("mid") Long memberId) {
        List<MemberReceiveAddressEntity> memberReceiveAddresses = memberReceiveAddressService.listMemberReceiveAddresses(memberId);

        List<MemberReceiveAddressTO> memberReceiveAddressTOList = memberReceiveAddresses.stream().map(item -> {
            MemberReceiveAddressTO memberReceiveAddressTO = new MemberReceiveAddressTO();
            BeanUtils.copyProperties(item, memberReceiveAddressTO);
            return memberReceiveAddressTO;
        }).collect(Collectors.toList());
        return MyResult.success(memberReceiveAddressTOList);
    }


    /**
     * 获取收货地址详情
     *
     * @param addressId 地址id
     * @return
     */
    @GetMapping("/{aid}/info")
    public MyResult<MemberReceiveAddressTO> getAddressInfoById(@PathVariable("aid") Long addressId) {
        MemberReceiveAddressEntity memberReceiveAddress = memberReceiveAddressService.getById(addressId);

        MemberReceiveAddressTO memberReceiveAddressTO = new MemberReceiveAddressTO();
        BeanUtils.copyProperties(memberReceiveAddress, memberReceiveAddressTO);
        return MyResult.success(memberReceiveAddressTO);
    }

}
