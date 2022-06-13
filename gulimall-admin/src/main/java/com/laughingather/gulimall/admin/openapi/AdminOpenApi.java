package com.laughingather.gulimall.admin.openapi;

import com.laughingather.gulimall.admin.entity.SysUserEntity;
import com.laughingather.gulimall.admin.entity.to.AdminInfoTO;
import com.laughingather.gulimall.admin.entity.to.AdminLoginTO;
import com.laughingather.gulimall.admin.entity.to.AdminTO;
import com.laughingather.gulimall.admin.service.SysUserService;
import com.laughingather.gulimall.common.api.MyResult;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 后台管理服务对外开放接口
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@RestController
@RequestMapping("/openapi/admin")
public class AdminOpenApi {

    @Resource
    private SysUserService sysUserService;

    /**
     * 远程用户名密码登录接口
     * <p>
     * 返回的数据就是一个JSON串，只要属性匹配就可以进行转换
     *
     * @param adminLoginTO 用户名密码传输类
     * @return 用户信息
     */
    @PostMapping("/login")
    MyResult<AdminTO> login(@RequestBody AdminLoginTO adminLoginTO) {
        AdminTO adminTO = sysUserService.login(adminLoginTO);
        return adminTO != null ? MyResult.success(adminTO) : MyResult.failed();
    }

    /**
     * 远程手机号验证码登录接口
     *
     * @param mobile 手机号
     * @return 用户信息
     */
    @PostMapping("/login/mobile")
    MyResult<AdminTO> loginByMobile(@RequestParam(name = "mobile") String mobile) {
        AdminTO adminTO = sysUserService.loginByMobile(mobile);
        return adminTO != null ? MyResult.success(adminTO) : MyResult.failed();
    }

    /**
     * 获取用户信息接口
     *
     * @param userid 用户id
     * @return 用户信息
     */
    @GetMapping("/userinfo")
    MyResult<AdminInfoTO> getUserinfo(@RequestParam("userid") Long userid) {
        SysUserEntity user = sysUserService.getUserById(userid);

        if (user == null) {
            return MyResult.failed();
        }

        AdminInfoTO adminInfoTO = new AdminInfoTO();
        BeanUtils.copyProperties(user, adminInfoTO);
        return MyResult.success(adminInfoTO);
    }

}

