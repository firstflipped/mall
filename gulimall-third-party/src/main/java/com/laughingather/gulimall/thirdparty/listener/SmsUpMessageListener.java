package com.laughingather.gulimall.thirdparty.listener;

import com.aliyun.mns.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 如果发送的短信需要接收对方回复的状态消息，只需实现该接口并初始化一个 Spring Bean 即可。
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
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