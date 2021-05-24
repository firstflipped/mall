package com.laughingather.gulimall.thirdparty.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author WangJie
 */

@Data
@Component
@ConfigurationProperties(prefix = "alibaba.cloud.sms")
public class SmsProperties {

    private String signName;

    private String templateCode;

}
