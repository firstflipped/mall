package com.flipped.mall.member.openapi;

import com.flipped.mall.common.entity.api.ErrorCodeEnum;
import com.flipped.mall.common.entity.api.MyResult;
import com.flipped.mall.common.exception.MobileExistException;
import com.flipped.mall.common.exception.UsernameExistException;
import com.flipped.mall.member.entity.MemberEntity;
import com.flipped.mall.member.entity.MemberReceiveAddressEntity;
import com.flipped.mall.member.entity.dto.*;
import com.flipped.mall.member.service.MemberReceiveAddressService;
import com.flipped.mall.member.service.MemberService;
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
     * @param memberRegisterDTO
     * @return
     */
    @PostMapping("/register")
    public MyResult<Void> register(@RequestBody MemberRegisterDTO memberRegisterDTO) {
        try {
            memberService.registerMember(memberRegisterDTO);
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
     * @param memberLoginDTO
     * @return
     */
    @PostMapping("/login")
    public MyResult<MemberDTO> login(@RequestBody MemberLoginDTO memberLoginDTO) {
        MemberDTO member = memberService.checkLogin(memberLoginDTO);
        return member == null ? MyResult.failed(ErrorCodeEnum.ACCOUNT_PASSWORD_INVALID_EXCEPTION) : MyResult.success(member);
    }


    /**
     * oauth2方式登录
     *
     * @param socialUser
     * @return
     */
    @PostMapping("/oauth2/login")
    public MyResult<MemberDTO> oauth2Login(@RequestBody SocialUser socialUser) {
        MemberDTO member = memberService.login(socialUser);
        return member == null ? MyResult.failed(ErrorCodeEnum.OAUTH_LOGIN_EXCEPTION) : MyResult.success(member);
    }


    /**
     * 获取会员信息
     *
     * @param memberId 会员id
     * @return 会员信息
     */
    @GetMapping("/{mid}/info")
    public MyResult<MemberDTO> getMember(@PathVariable("mid") Long memberId) {
        MemberEntity member = memberService.getById(memberId);

        MemberDTO memberDTO = new MemberDTO();
        BeanUtils.copyProperties(member, memberDTO);
        return MyResult.success(memberDTO);
    }


    /**
     * 获取会员收货地址列表
     *
     * @param memberId 会员id
     * @return
     */
    @GetMapping("/{mid}/address")
    public MyResult<List<MemberReceiveAddressDTO>> listMemberReceiveAddresses(@PathVariable("mid") Long memberId) {
        List<MemberReceiveAddressEntity> memberReceiveAddresses = memberReceiveAddressService.listMemberReceiveAddresses(memberId);

        List<MemberReceiveAddressDTO> memberReceiveAddressDTOList = memberReceiveAddresses.stream().map(item -> {
            MemberReceiveAddressDTO memberReceiveAddressDTO = new MemberReceiveAddressDTO();
            BeanUtils.copyProperties(item, memberReceiveAddressDTO);
            return memberReceiveAddressDTO;
        }).collect(Collectors.toList());
        return MyResult.success(memberReceiveAddressDTOList);
    }


    /**
     * 获取收货地址详情
     *
     * @param addressId 地址id
     * @return
     */
    @GetMapping("/{aid}/info")
    public MyResult<MemberReceiveAddressDTO> getAddressInfoById(@PathVariable("aid") Long addressId) {
        MemberReceiveAddressEntity memberReceiveAddress = memberReceiveAddressService.getById(addressId);

        MemberReceiveAddressDTO memberReceiveAddressDTO = new MemberReceiveAddressDTO();
        BeanUtils.copyProperties(memberReceiveAddress, memberReceiveAddressDTO);
        return MyResult.success(memberReceiveAddressDTO);
    }

}
