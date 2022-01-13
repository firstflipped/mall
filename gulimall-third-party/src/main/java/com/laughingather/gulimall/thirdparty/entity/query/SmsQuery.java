package com.laughingather.gulimall.thirdparty.entity.query;

import com.laughingather.gulimall.common.query.PageQuery;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * 短信查询实体
 *
 * @author：laughingather
 * @create：2022-01-13 2022/1/13
 */
@Data
public class SmsQuery extends PageQuery {

    /**
     * 手机号码
     */
    @NotBlank(message = "手机号码不能为空")
    private String phoneNumber;

    /**
     * 日期
     */
    @NotNull(message = "日期不能为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

}

