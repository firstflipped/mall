package com.laughingather.gulimall.thirdparty.service;

import com.laughingather.gulimall.thirdparty.entity.param.MailMessageParam;

/**
 * 邮箱发送逻辑接口
 *
 * @author：laughingather
 * @create：2022-01-13 2022/1/13
 */
public interface MailService {

    /**
     * 发送简单邮件
     *
     * @param mailMessageParam
     */
    void sendSimpleMail(MailMessageParam mailMessageParam);
}
