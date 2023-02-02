package com.laughingather.gulimall.admin.openapi;

import com.laughingather.gulimall.admin.entity.SysUserEntity;
import com.laughingather.gulimall.admin.entity.dto.AdminDTO;
import com.laughingather.gulimall.admin.entity.dto.AdminInfoDTO;
import com.laughingather.gulimall.admin.entity.dto.AdminLoginDTO;
import com.laughingather.gulimall.admin.service.SysUserService;
import com.laughingather.gulimall.common.annotation.PlatformLogAnnotation;
import com.laughingather.gulimall.common.entity.api.MyResult;
import com.laughingather.gulimall.common.constant.LogConstants;
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
     * 返回的数据就是一个JSON串，只要属性匹配就可以进行转换，名称不强制要求一致
     *
     * @param adminLoginDTO 用户名密码传输类
     * @return 用户信息
     */
    @PostMapping("/login")
    @PlatformLogAnnotation(value = "管理员用户登录", login = LogConstants.LOGIN)
    MyResult<AdminDTO> login(@RequestBody AdminLoginDTO adminLoginDTO) {
        AdminDTO adminDTO = sysUserService.login(adminLoginDTO);
        return adminDTO != null ? MyResult.success(adminDTO) : MyResult.failed();
    }

    /**
     * 远程手机号验证码登录接口
     *
     * @param mobile 手机号
     * @return 用户信息
     */
    @PostMapping("/login/mobile")
    MyResult<AdminDTO> loginByMobile(@RequestParam(name = "mobile") String mobile) {
        AdminDTO adminDTO = sysUserService.loginByMobile(mobile);
        return adminDTO != null ? MyResult.success(adminDTO) : MyResult.failed();
    }

    /**
     * 获取用户信息接口
     *
     * @param userid 用户id
     * @return 用户信息
     */
    @GetMapping("/userinfo")
    MyResult<AdminInfoDTO> getUserinfo(@RequestParam("userid") Long userid) {
        SysUserEntity user = sysUserService.getUserById(userid);

        if (user == null) {
            return MyResult.failed();
        }

        AdminInfoDTO adminInfoDTO = new AdminInfoDTO();
        BeanUtils.copyProperties(user, adminInfoDTO);
        return MyResult.success(adminInfoDTO);
    }

}

