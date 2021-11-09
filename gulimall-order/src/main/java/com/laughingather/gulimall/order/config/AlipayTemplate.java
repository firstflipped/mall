package com.laughingather.gulimall.order.config;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.laughingather.gulimall.order.entity.dto.PayDTO;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 支付配置类
 *
 * @author laughingather
 */
@Data
@Log4j2
@Component
@ConfigurationProperties(prefix = "alipay")
public class AlipayTemplate {

    /**
     * 在支付宝创建的应用的id
     */
    private String app_id = "2021000118648149";

    /**
     * 商户私钥，您的PKCS8格式RSA2私钥
     */
    private String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCk1caIXtrnInM8TPpsvFHJ1hwiN8XQ+UT89Zve+C5wLQ84RVot6weSZIYGffPUClzV/pLbc3pO1x7FeLlT98O4Pt4MKQgo5R5r2JNGWemM1VqYIpy9tgVcCYD2H147QoA77BXysLJewWNsCyIa2ICvJIOilcs/gjNgJpHuvSBZ2He6RRb+NgL3nKSqR77xR8IxXVDzOIodPnjhCnBuzcJ4wnlGy54OSL355upQNV+6AgrnGyacj+uulDK/0+9tXSWTwOyRUgDwRhsrRE9rsaYpJguZqUDGnUjUZaLLNgkFLLN0Gv2Jg5xqcGIULloyZq3g3J/3Ytbi82YZc1U8YHa5AgMBAAECggEAUHePoJj1senOaXyGr7hLEQX12/pwFjDprbdUrUdonBhTGrw4ZrwaiSuLhfXMpxGb+ld5Z6n65iv7+JiTmFsWb4Y5LaSGODiJXkpusuRHsBmjTStwauhFu4VkMEJl5PX01cvNdxktKjasC11v+7z/LQDdRexsOQedfu21Rd+LwG+zPJDiyfUxl9NMlgZjc5CAUn/1RuLIXXLdZS6k+Sr/jlcsd6+hEPqQ0asYBttDxABEwEiMfu3TNbrnTPF6d+xbGwppYqiw6HLHLx4E8pmB9+fR5I/5yizWeoxKZiOwdcYxZBeaRDJlv6p3lMggMCpohKOlNhgEvHoV7M2q7OruAQKBgQDmPfTBQzE0M8RxaphJ0OFkIH4k7Of0w5tgGWRALEmyoS6ixMAFuXNxOR38Mdp/2MpDCX/39BUG9/pdCm2LmudHH02tk58nOv455MuO+QmR8o0y49IJAmDOGXaJ6aresF+LCUktwt6dCc9qeOd6HErRJO4mF9z0UP7FBdsK9JEweQKBgQC3RpbNr5/aaliHaqWgqA3OEwvGiB5i69fenWI0U4MGG/h6O6ENwJBPcqHqgptInM+HJKCX3H8yfm1/MeSZJotSSPj3yGoT9bKHvpmvui45SDXqHG6j4IJo08Pnd37m4XuifVwUOXr2/bm05udb0racUdO4KznIIN5G8A3rBeJoQQKBgQDb9mblWB8i4y4SuKajJtaeBNjeyCJWWaYQKlH3aRhDeu+F+3gEQAdFLS8zaCF6MJATeXjzVxHbpX9f8f1z+VppPUPFx5muLhMBFk9O2SdGcrZo3vQjPU1kuTjhSwa+VzwAJa/u8xQgbWXmL3nd1De6FZEbfYLPOq4bZpu8nKg06QKBgAteLIJrj0275OvZWJajdd+/XK6eyMu39zbOqkW2nFznpTznr/CrbggPgy2Jllolp9WpFvD0c0tNfjsyfAxn0KJ09kRgQPwl9EHpN50nvjA9Y2K3CsHNbilUYUVdKxjlCYcloW17tz3UtQ0ceWM/qaX+iVRWEgpwx3i88RAP2GBBAoGALVsiFaaAiLdppL1s6b4Qz34jAQUrx0++FgSiXy/AbCvzPbxcSGkBoDzUekiq58qajqOC6cWQ+hUJ923gKYJykASO5Xn5hgnf6Jsmz9mit1dSvacPKKTnUJBA4RKQQHwyKUndy19ZCMMyxXYRLOF/FXEcLQ9LhbQ4N3Tfg8353wg=";

    /**
     * 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
     */
    private String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvKmMqt7lXOdI9rt+wEzFvq/q1+UYAQy76pG2cjn2O1/gOw5Rdk9GvXJxdnTQdD6Rnwht7N4J+9vAlCfP9xuAoS5oxp/t3KnE0H7tMnbsWgdF3qOlOond14fw18wtFHxrv3d2c4Y+pNiCOvxDLBDUdy9WPsEhpui3C6GBVeJSSNAcjiIbVYJPG8vFr9Qw7uvAP+wAjIv11zGbARVc6zgV+7+uVzsYM0k+cEEIB/qaIsQWV/MrK3W49rFL3JXeWE3tFirM01IlvpO7Iy33EEQjlzv35Qm4Gi8qF7U3AHwuMBA9R1ayppJ/ner7ly8IHQv1ySh8g3LnENzyXwF6rePbYwIDAQAB";

    /**
     * 服务器[异步通知]页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
     * 支付宝会悄悄的给我们发送一个请求，告诉我们支付成功的信息
     */
    private String notify_url;

    /**
     * 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
     * 同步通知，支付成功，一般跳转到成功页
     */
    private String return_url = "http://member.gulimall.com/memberOrder.html";

    /**
     * 签名方式
     */
    private String sign_type = "RSA2";

    /**
     * 字符编码格式
     */
    private String charset = "utf-8";

    /**
     * 支付宝网关； https://openapi.alipaydev.com/gateway.do
     */
    private String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";


    /**
     * 发起支付请求
     *
     * @param payDTO
     * @return
     * @throws AlipayApiException
     */
    public String pay(PayDTO payDTO) throws AlipayApiException {

        // AlipayClient alipayClient = new DefaultAlipayClient(AlipayTemplate.gatewayUrl, AlipayTemplate.app_id, AlipayTemplate.merchant_private_key, "json", AlipayTemplate.charset, AlipayTemplate.alipay_public_key, AlipayTemplate.sign_type);
        // 1、根据支付宝的配置生成一个支付客户端
        AlipayClient alipayClient = new DefaultAlipayClient(gatewayUrl,
                app_id, merchant_private_key, "json",
                charset, alipay_public_key, sign_type);

        // 2、创建一个支付请求 //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(return_url);
        alipayRequest.setNotifyUrl(notify_url);

        // 拼装请求参数
        JSONObject bizContent = new JSONObject();
        bizContent.put("out_trade_no", payDTO.getOut_trade_no());
        bizContent.put("subject", payDTO.getSubject());
        bizContent.put("total_amount", payDTO.getTotal_amount());
        bizContent.put("product_code", "FAST_INSTANT_TRADE_PAY");
        bizContent.put("body", payDTO.getBody());
        alipayRequest.setBizContent(bizContent.toString());

        AlipayTradePagePayResponse alipayTradePagePayResponse = alipayClient.pageExecute(alipayRequest);
        if (alipayTradePagePayResponse.isSuccess()) {
            // 会收到支付宝的响应，响应的是一个页面，只要浏览器显示这个页面，就会自动来到支付宝的收银台页面
            return alipayTradePagePayResponse.getBody();
        } else {
            log.info("调用支付宝服务失败");
            return "";
        }
    }
}
