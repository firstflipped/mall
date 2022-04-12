package com.laughingather.gulimall.thirdparty.service.impl;

import com.laughingather.gulimall.thirdparty.entity.param.MailMessageParam;
import com.laughingather.gulimall.thirdparty.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 邮箱发送逻辑实现
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Slf4j
@Service
public class MailServiceImpl implements MailService {

    @Resource
    private JavaMailSender javaMailSender;

    @Override
    public void sendSimpleMail(MailMessageParam mailMessageParam) {

        // 构建一个邮件对象
        SimpleMailMessage message = new SimpleMailMessage();
        BeanUtils.copyProperties(mailMessageParam, message);

        // 设置邮件发送日期
        message.setSentDate(new Date());

        // 发送邮件
        javaMailSender.send(message);
    }


}

