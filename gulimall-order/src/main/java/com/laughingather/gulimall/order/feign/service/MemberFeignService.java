package com.laughingather.gulimall.order.feign.service;

import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.order.feign.entity.MemberReceiveAddressTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 会员服务远程调用
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@FeignClient("gulimall-member")
@RequestMapping("/gulimall-member/openapi/member")
public interface MemberFeignService {

    /**
     * 调用会员服务获取会员收货地址
     *
     * @param memberId 会员id
     * @return 会员收货地址列表
     */
    @GetMapping("/{mid}/addresses")
    MyResult<List<MemberReceiveAddressTO>> listMemberReceiveAddress(@PathVariable("mid") Long memberId);


}
