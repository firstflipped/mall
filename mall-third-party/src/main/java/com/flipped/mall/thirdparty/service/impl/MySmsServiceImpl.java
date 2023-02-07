package com.flipped.mall.thirdparty.service.impl;

import com.alibaba.cloud.spring.boot.sms.ISmsService;
import com.aliyuncs.dysmsapi.model.v20170525.*;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.flipped.mall.thirdparty.config.SmsProperties;
import com.flipped.mall.thirdparty.entity.query.SmsQuery;
import com.flipped.mall.thirdparty.service.MySmsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * 短信发送逻辑实现
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Slf4j
@Service("mySmsService")
public class MySmsServiceImpl implements MySmsService {


    @Resource
    private SmsProperties smsProperties;
    @Resource
    private ISmsService aliSmsService;


    @Override
    public SendSmsResponse sendCheckCode(String mobile, String code) {
        SendSmsResponse sendSmsResponse = null;

        // 组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        // 必填:待发送手机号
        request.setPhoneNumbers(mobile);
        // 必填:短信签名-可在短信控制台中找到
        request.setSignName(smsProperties.getSignName());
        // 必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(smsProperties.getTemplateCode());
        // 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        request.setTemplateParam("{\"code\":\"" + code + "\"}");

        // 选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        // request.setSmsUpExtendCode("90997");

        // 可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("****TraceId");
        try {
            sendSmsResponse = aliSmsService.sendSmsRequest(request);
        } catch (ClientException e) {
            log.error(e.getMessage());
        }

        return sendSmsResponse;
    }

    @Override
    public SendBatchSmsResponse batchSendCheckCode(String mobile, String code) {
        // 组装请求对象
        SendBatchSmsRequest request = new SendBatchSmsRequest();
        // 使用post提交
        request.setSysMethod(MethodType.GET);
        // 必填:待发送手机号。支持JSON格式的批量调用，批量上限为100个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
        request.setPhoneNumberJson("[" + mobile + "]");
        // 必填:短信签名-支持不同的号码发送不同的短信签名
        request.setSignNameJson("[" + smsProperties.getSignName() + "]");
        // 必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(smsProperties.getTemplateCode());
        // 必填:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        // 友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
        request.setTemplateParamJson("[{\"code\":\"" + code + "\"},{\"code\":\"" + code + "\"}]");
        // 可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
        // request.setSmsUpExtendCodeJson("[\"90997\",\"90998\"]");

        try {
            return aliSmsService.sendSmsBatchRequest(request);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public QuerySendDetailsResponse querySendDetails(SmsQuery smsQuery) {
        QuerySendDetailsResponse querySendDetailsResponse = null;

        // 校验查询时间是否在30天内
        LocalDate date = smsQuery.getDate();
        LocalDate now = LocalDate.now();
        boolean notWithIn30Days = date.isAfter(now) || date.isBefore(now.minus(30L, ChronoUnit.DAYS));
        if (notWithIn30Days) {
            return null;
        }

        // 组装请求对象
        QuerySendDetailsRequest request = new QuerySendDetailsRequest();
        // 必填-号码
        request.setPhoneNumber(smsQuery.getPhoneNumber());
        // 必填-短信发送的日期 支持30天内记录查询（可查其中一天的发送数据），格式yyyyMMdd
        request.setSendDate(date.format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        // 必填-当前页码从1开始计数
        request.setCurrentPage(smsQuery.getPn());
        // 必填-页大小
        request.setPageSize(smsQuery.getPs());

        try {
            querySendDetailsResponse = aliSmsService.querySendDetails(request);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return querySendDetailsResponse;
    }


}

