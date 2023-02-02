package com.laughingather.gulimall.thirdparty.controller;

import com.laughingather.gulimall.common.entity.api.MyResult;
import com.laughingather.gulimall.thirdparty.service.OssService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * oss文件上传路由
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@RestController
@RequestMapping("/oss")
public class OssController {

    @Resource
    private OssService ossService;

    @GetMapping("/policy")
    public MyResult<Map<String, String>> policy() {
        Map<String, String> policy = ossService.policy();
        return MyResult.success(policy);
    }

}
