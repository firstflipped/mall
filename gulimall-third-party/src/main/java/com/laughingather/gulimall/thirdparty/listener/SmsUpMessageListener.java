package com.laughingather.gulimall.thirdparty.listener;

import com.aliyun.mns.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 如果发送的短信需要接收对方回复的状态消息，只需实现该接口并初始化一个 Spring Bean 即可。
 *
 * @author：laughingather
 */
@Slf4j
@Component
public class SmsUpMessageListener implements com.alibaba.cloud.spring.boot.sms.SmsUpMessageListener {

    @Override
    public boolean dealMessage(Message message) {
        log.error(this.getClass().getName() + "; " + message.toString());
        return true;
    }

}