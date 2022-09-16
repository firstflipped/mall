package com.laughingather.gulimall.order.feign.service;

import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.order.feign.entity.MemberDTO;
import com.laughingather.gulimall.order.feign.entity.MemberReceiveAddressDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * 会员服务远程调用
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@FeignClient("gulimall-member")
public interface MemberFeignService {

    /**
     * 调用会员服务获取会员收货地址
     *
     * @param memberId 会员id
     * @return 会员收货地址列表
     */
    @GetMapping("/gulimall-member/openapi/member/{mid}/addresses")
    MyResult<List<MemberReceiveAddressDTO>> listMemberReceiveAddress(@PathVariable("mid") Long memberId);


    /**
     * 获取会员信息
     *
     * @param memberId 会员id
     * @return
     */
    @GetMapping("/gulimall-member/openapi/member/{mid}/info")
    MyResult<MemberDTO> getMember(@PathVariable("mid") Long memberId);


}
