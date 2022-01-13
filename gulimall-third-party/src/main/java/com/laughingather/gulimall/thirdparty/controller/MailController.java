package com.laughingather.gulimall.thirdparty.controller;

import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.thirdparty.entity.param.MailMessageParam;
import com.laughingather.gulimall.thirdparty.service.MailService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 邮件发送路由
 *
 * @author：laughingather
 * @create：2022-01-12 2022/1/12
 */
@RestController
@RequestMapping("/mail")
public class MailController {

    @Resource
    private MailService mailService;

    @PostMapping("/simple-mail-send")
    public MyResult<Void> sendSimpleMail(@RequestBody MailMessageParam mailMessageParam) {
        mailService.sendSimpleMail(mailMessageParam);
        return MyResult.success();
    }


}

