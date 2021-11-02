package com.laughingather.gulimall.thirdparty.service;

import com.laughingather.gulimall.thirdparty.domain.Message;

/**
 * 发送消息
 *
 * @author：laughingather
 * @create：2021-11-02 2021/11/2
 */
@FunctionalInterface
public interface MessageService {

    /**
     * 发送消息
     *
     * @param message 消息
     */
    void send(Message message);

}
