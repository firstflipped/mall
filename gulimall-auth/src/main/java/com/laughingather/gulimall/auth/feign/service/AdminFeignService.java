package com.laughingather.gulimall.auth.feign.service;

import com.laughingather.gulimall.auth.entity.to.AdminLoginTO;
import com.laughingather.gulimall.auth.feign.entity.AdminInfoTO;
import com.laughingather.gulimall.auth.feign.entity.AdminTO;
import com.laughingather.gulimall.common.api.MyResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * 后台管理服务远程调用接口
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@FeignClient("gulimall-admin")
@RequestMapping("/gulimall-admin/openapi/admin")
public interface AdminFeignService {


    /**
     * 远程登录接口
     * <p>
     * 返回的数据就是一个JSON串，只要属性匹配就可以进行转换
     *
     * @param adminLoginTO
     * @return
     */
    @PostMapping("/login")
    MyResult<AdminTO> login(@RequestBody AdminLoginTO adminLoginTO);

    /**
     * 远程获取用户信息接口
     *
     * @param userid
     * @return
     */
    @GetMapping("/userinfo")
    MyResult<AdminInfoTO> getUserinfo(@RequestParam("userid") Long userid);
}
