package com.flipped.mall.thirdparty.entity.param;

import com.flipped.mall.common.constant.ThirdPartyConstants;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 邮件消息传入实体
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Data
public class MailMessageParam {

    /**
     * 邮件发送者
     */
    private String from = ThirdPartyConstants.MAIL_FROM;

    /**
     * 邮件接收者，可以有多个接收者，中间用逗号隔开
     */
    @NotNull
    private String[] to;

    /**
     * 件抄送人，可以有多个抄送人
     */
    private String[] cc;

    /**
     * 隐秘抄送人，可以有多个
     */
    private String[] bcc;

    /**
     * 邮件主题
     */
    @NotBlank
    private String subject;

    /**
     * 邮件正文
     */
    @NotBlank
    private String text;

}

