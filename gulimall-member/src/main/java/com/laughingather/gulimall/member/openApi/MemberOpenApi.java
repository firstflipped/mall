package com.laughingather.gulimall.member.openApi;

import com.laughingather.gulimall.common.api.ErrorCodeEnum;
import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.member.entity.MemberEntity;
import com.laughingather.gulimall.member.entity.dto.MemberLoginDTO;
import com.laughingather.gulimall.member.entity.dto.MemberRegisterDTO;
import com.laughingather.gulimall.member.exception.MobileExistException;
import com.laughingather.gulimall.member.exception.UsernameExistException;
import com.laughingather.gulimall.member.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


    @PostMapping("/login")
    public MyResult memberLogin(@RequestBody MemberLoginDTO memberLoginDTO) {

        MemberEntity member = memberService.checkLogin(memberLoginDTO);
        return member == null ? MyResult.builder().code(ErrorCodeEnum.ACCOUNT_PASSWORD_INVAILD_EXCEPTION.getCode())
                .message(ErrorCodeEnum.ACCOUNT_PASSWORD_INVAILD_EXCEPTION.getMessage()).build() : MyResult.success();
    }

}
