package com.laughingather.gulimall.adminnew.openapi;

import com.laughingather.gulimall.adminnew.entity.dto.UserDTO;
import com.laughingather.gulimall.adminnew.entity.dto.UserLoginDTO;
import com.laughingather.gulimall.adminnew.service.SysUserService;
import com.laughingather.gulimall.common.api.MyResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
     * @param userLoginDTO
     * @return
     */
    @PostMapping("/login")
    MyResult<UserDTO> login(@RequestBody UserLoginDTO userLoginDTO) {
        UserDTO userDTO = sysUserService.checkLogin(userLoginDTO);
        return userDTO != null ? MyResult.success(userDTO) : MyResult.failed();
    }

}

