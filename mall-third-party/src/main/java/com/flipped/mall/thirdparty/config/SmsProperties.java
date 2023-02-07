package com.flipped.mall.thirdparty.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 短信配置类
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
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
