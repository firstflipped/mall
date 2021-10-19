package com.laughingather.gulimall.member.openapi;

import com.laughingather.gulimall.common.api.ErrorCodeEnum;
import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.member.entity.MemberEntity;
import com.laughingather.gulimall.member.entity.MemberReceiveAddressEntity;
import com.laughingather.gulimall.member.entity.dto.MemberLoginDTO;
import com.laughingather.gulimall.member.entity.dto.MemberRegisterDTO;
import com.laughingather.gulimall.member.entity.dto.SocialUser;
import com.laughingather.gulimall.member.exception.MobileExistException;
import com.laughingather.gulimall.member.exception.UsernameExistException;
import com.laughingather.gulimall.member.service.MemberReceiveAddressService;
import com.laughingather.gulimall.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 会员服务对外提供接口
 *
 * @author：laughingather
 * @create：2021-05-26 22:44
 */
@Slf4j
@RestController
@RequestMapping("/openapi/member")
public class MemberOpenApi {

    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberReceiveAddressService memberReceiveAddressService;

    /**
     * 用户注册
     *
     * @param memberRegisterDTO
     * @return
     */
    @PostMapping("/register")
    public MyResult memberRegister(@RequestBody MemberRegisterDTO memberRegisterDTO) {
        try {
            memberService.registerMember(memberRegisterDTO);
        } catch (UsernameExistException e) {
            return MyResult.builder().code(ErrorCodeEnum.USERNAME_EXIST_EXCEPTION.getCode())
                    .message(ErrorCodeEnum.MOBILE_EXIST_EXCEPTION.getMessage()).build();
        } catch (MobileExistException e) {
            return MyResult.builder().code(ErrorCodeEnum.USERNAME_EXIST_EXCEPTION.getCode())
                    .message(ErrorCodeEnum.MOBILE_EXIST_EXCEPTION.getMessage()).build();
        }

        return MyResult.success();
    }


    /**
     * 用户登录
     *
     * @param memberLoginDTO
     * @return
     */
    @PostMapping("/login")
    public MyResult<MemberEntity> memberLogin(@RequestBody MemberLoginDTO memberLoginDTO) {
        MemberEntity member = memberService.checkLogin(memberLoginDTO);
        return member == null ? MyResult.<MemberEntity>builder().code(ErrorCodeEnum.ACCOUNT_PASSWORD_INVAILD_EXCEPTION.getCode())
                .message(ErrorCodeEnum.ACCOUNT_PASSWORD_INVAILD_EXCEPTION.getMessage()).build() : MyResult.success(member);
    }


    /**
     * oauth2方式登录
     *
     * @param socialUser
     * @return
     */
    @PostMapping("/oauth2/login")
    public MyResult<MemberEntity> oauth2Login(@RequestBody SocialUser socialUser) {
        MemberEntity member = memberService.login(socialUser);
        return member == null ? MyResult.<MemberEntity>builder().code(ErrorCodeEnum.OAUTH_LOGIN_EXCEPTION.getCode())
                .message(ErrorCodeEnum.OAUTH_LOGIN_EXCEPTION.getMessage()).build() : MyResult.success(member);
    }


    /**
     * 获取会员收货地址列表
     *
     * @param memberId
     * @return
     */
    @GetMapping("/{member-id}/addresses")
    public MyResult<List<MemberReceiveAddressEntity>> listMemberReceiveAddress(@PathVariable("member-id") Long memberId) {
        List<MemberReceiveAddressEntity> memberReceiveAddress = memberReceiveAddressService.listMemberReceiveAddress(memberId);
        return MyResult.success(memberReceiveAddress);
    }


    /**
     * 获取收货地址详情
     *
     * @param id
     * @return
     */
    @GetMapping("/{aid}/info")
    public MyResult<MemberReceiveAddressEntity> getAddressInfoById(@PathVariable("aid") Long id) {
        MemberReceiveAddressEntity memberReceiveAddress = memberReceiveAddressService.getById(id);
        return MyResult.success(memberReceiveAddress);
    }

}
