package com.laughingather.gulimall.auth.feign.service;

import com.laughingather.gulimall.auth.entity.to.AdminLoginTO;
import com.laughingather.gulimall.auth.feign.entity.AdminTO;
import com.laughingather.gulimall.common.api.MyResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 后台管理第三方调用接口
 *
 * @author：laughingather
 * @create：2021-12-06 2021/12/6
 */
@FeignClient("gulimall-admin-new")
@RequestMapping("/gulimall-admin-new/openapi/admin")
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

}
