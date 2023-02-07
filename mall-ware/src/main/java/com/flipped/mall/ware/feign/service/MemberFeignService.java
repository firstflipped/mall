package com.flipped.mall.ware.feign.service;

import com.flipped.mall.common.entity.api.MyResult;
import com.flipped.mall.ware.feign.entity.MemberReceiveAddressDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 远程调用会员服务
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@FeignClient("gulimall-member")
public interface MemberFeignService {

    /**
     * 获取地址详情信息
     *
     * @param id
     * @return
     */
    @GetMapping("/gulimall-member/openapi/member/{aid}/info")
    MyResult<MemberReceiveAddressDTO> getAddressInfoById(@PathVariable("aid") Long id);

}
