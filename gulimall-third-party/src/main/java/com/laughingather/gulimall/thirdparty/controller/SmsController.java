package com.laughingather.gulimall.thirdparty.controller;

import com.aliyun.mns.model.Message;
import com.aliyuncs.dysmsapi.model.v20170525.QuerySendDetailsResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendBatchSmsResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.laughingather.gulimall.common.api.ErrorCodeEnum;
import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.thirdparty.entity.query.SmsQuery;
import com.laughingather.gulimall.thirdparty.listener.SmsReportMessageListener;
import com.laughingather.gulimall.thirdparty.service.MySmsService;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 短信发送路由
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@RestController
@RequestMapping("/sms")
public class SmsController {

    @Resource
    private Environment environment;
    @Resource
    private SmsReportMessageListener smsReportMessageListener;

    @Resource
    private MySmsService mySmsService;

    @GetMapping("/sms-send")
    public MyResult<SendSmsResponse> sendCheckCode(@RequestParam(name = "phoneNumber") String phoneNumber,
                                                   @RequestParam(name = "code") String code) {
        SendSmsResponse sendSmsResponse = mySmsService.sendCheckCode(phoneNumber, code);
        return sendSmsResponse != null ? MyResult.success(sendSmsResponse) : MyResult.failed(ErrorCodeEnum.SEND_SMS_EXCEPTION);
    }

    @GetMapping("/batch-sms-send")
    public MyResult<SendBatchSmsResponse> batchSendCheckCode(@RequestParam(name = "phoneNumber") String phoneNumber,
                                                             @RequestParam(name = "code") String code) {
        SendBatchSmsResponse sendBatchSmsResponse = mySmsService.batchSendCheckCode(phoneNumber, code);
        return sendBatchSmsResponse != null ? MyResult.success(sendBatchSmsResponse) : MyResult.failed(ErrorCodeEnum.SEND_SMS_EXCEPTION);
    }

    @GetMapping("/query")
    public MyResult<QuerySendDetailsResponse> querySendDetails(@ModelAttribute SmsQuery smsQuery) {
        QuerySendDetailsResponse querySendDetails = mySmsService.querySendDetails(smsQuery);
        return querySendDetails != null ? MyResult.success(querySendDetails) : MyResult.failed(ErrorCodeEnum.SEND_SMS_EXCEPTION);
    }

    @GetMapping("/sms-report")
    public List<Message> smsReport() {
        return smsReportMessageListener.getSmsReportMessageSet();
    }


    @GetMapping("/report-queue")
    public String getSmsReportQueueName() {
        return environment.getProperty("alibaba.cloud.sms.up-queue-name");
    }

}