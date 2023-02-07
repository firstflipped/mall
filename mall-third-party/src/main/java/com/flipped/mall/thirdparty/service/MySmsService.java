package com.flipped.mall.thirdparty.service;

import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendBatchSmsResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.flipped.mall.thirdparty.entity.query.SmsQuery;

/**
 * 自定义短信发送逻辑接口
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
public interface MySmsService {

    /**
     * 发送短信
     *
     * @param mobile 手机号
     * @param code   验证码
     * @return
     */
    SendSmsResponse sendCheckCode(String mobile, String code);


    /**
     * 批量发送短信
     *
     * @param mobile 手机号
     * @param code   验证码
     * @return
     */
    SendBatchSmsResponse batchSendCheckCode(String mobile, String code);


    /**
     * 查询历史短信详情
     *
     * @param smsQuery
     * @return
     */
    QuerySendDetailsResponse querySendDetails(SmsQuery smsQuery);

}

