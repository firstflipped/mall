package com.laughingather.gulimall.auth.openapi;

import com.laughingather.gulimall.auth.service.AuthService;
import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.common.entity.JwtPayLoad;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * 权限服务对外开放接口
 *
 * @author：laughingather
 * @create：2022-01-06 2022/1/6
 */
@RestController
@RequestMapping("/openapi/auth")
public class AuthOpenApi {

    @Resource
    private AuthService authService;

    @GetMapping("/token/check")
    public MyResult<Void> checkToken(@RequestParam("token") String token) {
        Boolean success = authService.checkToken(token);
        return success ? MyResult.success() : MyResult.failed();
    }


    @GetMapping("/token/parse")
    public MyResult<JwtPayLoad> parseToken(@RequestParam("token") String token) {
        JwtPayLoad jwtPayLoad = authService.parseToken(token);
        return !Objects.isNull(jwtPayLoad) ? MyResult.success(jwtPayLoad) : MyResult.failed();
    }

}

