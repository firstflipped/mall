package com.laughingather.gulimall.thirdparty.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 短信配置类
 *
 * @author：laughingather
 */
@Data
@Component
@ConfigurationProperties(prefix = "alibaba.cloud.sms")
public class SmsProperties {

    /**
     * 签名
     */
    private String signName;

    /**
     * 模板编号
     */
    private String templateCode;

}
