package com.laughingather.gulimall.thirdparty.service;

import com.laughingather.gulimall.thirdparty.entity.param.MailMessageParam;

/**
 * 邮箱发送逻辑接口
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
public interface MailService {

    /**
     * 发送简单邮件
     *
     * @param mailMessageParam
     */
    void sendSimpleMail(MailMessageParam mailMessageParam);
}
