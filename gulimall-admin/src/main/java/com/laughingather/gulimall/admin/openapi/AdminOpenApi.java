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
 * 后台管理对外开放接口
 *
 * @author：laughingather
 * @create：2021-12-06 2021/12/6
 */
@RestController
@RequestMapping("/openapi/admin")
public class AdminOpenApi {

    @Resource
    private SysUserService sysUserService;

    /**
     * 远程登录接口
     * <p>
     * 返回的数据就是一个JSON串，只要属性匹配就可以进行转换
     *
     * @param adminLoginTO
     * @return
     */
    @PostMapping("/login")
    MyResult<AdminTO> login(@RequestBody AdminLoginTO adminLoginTO) {
        AdminTO adminTO = sysUserService.checkLogin(adminLoginTO);
        return adminTO != null ? MyResult.success(adminTO) : MyResult.failed();
    }


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

