package com.laughingather.gulimall.ware.feign.service;

import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.ware.feign.entity.MemberReceiveAddressTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 远程调用会员服务
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@FeignClient("gulimall-member")
@RequestMapping("/gulimall-member/openapi/member")
public interface MemberFeignService {

    /**
     * 获取地址详情信息
     *
     * @param id
     * @return
     */
    @GetMapping("/{aid}/info")
    MyResult<MemberReceiveAddressTO> getAddressInfoById(@PathVariable("aid") Long id);

}
