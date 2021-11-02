package com.laughingather.gulimall.thirdparty.service;

/**
 * 发送信息
 *
 * @author：laughingather
 * @create：2021-11-02 2021/11/2
 */
public interface SendMessageService {

    /**
     * 发送邮箱
     *
     * @param messageService
     */
    void sendMailMessage(MessageService messageService);

}
