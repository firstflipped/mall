package com.flipped.mall.admin.entity.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentStyle;
import com.alibaba.excel.annotation.write.style.HeadStyle;
import com.alibaba.excel.enums.poi.HorizontalAlignmentEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 日志列表导出视图
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-25 17:24:06
 */
@Data
@EqualsAndHashCode
@HeadStyle(horizontalAlignment = HorizontalAlignmentEnum.CENTER) // 头居中显示
@ContentStyle(horizontalAlignment = HorizontalAlignmentEnum.CENTER) // 内容居中显示
public class PlatformLogExcelVO {

    /**
     * 请求URI
     */
    @ExcelProperty("请求URI")
    @ColumnWidth(30)
    private String requestUri;

    /**
     * 请求URL
     */
    @ExcelProperty("请求URL")
    @ColumnWidth(50)
    private String requestUrl;

    /**
     * 请求方法
     */
    @ExcelProperty("请求方法")
    @ColumnWidth(10)
    private String requestMethod;

    /**
     * 请求参数
     */
    @ExcelProperty("请求参数")
    @ColumnWidth(80)
    private String requestParams;

    /**
     * 请求类
     */
    @ExcelProperty("请求类")
    @ColumnWidth(60)
    private String className;

    /**
     * 请求函数
     */
    @ExcelProperty("请求函数")
    @ColumnWidth(20)
    private String methodName;

    /**
     * 操作类型
     */
    @ExcelProperty(value = "操作类型")
    @ColumnWidth(20)
    private String methodType;

    /**
     * 服务器IP地址
     */
    @ExcelProperty("服务器IP地址")
    @ColumnWidth(30)
    private String serverIp;

    /**
     * 客户端IP地址
     */
    @ExcelProperty("客户端IP地址")
    @ColumnWidth(30)
    private String clientIp;

    /**
     * 返回结果，是否成功
     * 1 成功
     * 0 失败
     */
    @ExcelProperty("是否成功")
    @ColumnWidth(10)
    private String success;

    /**
     * 是否是登录操作
     * 1 是
     * 0 否
     */
    @ExcelProperty("是否是登录操作")
    @ColumnWidth(10)
    private String login;

    /**
     * 消耗时间
     */
    @ExcelProperty("消耗时间（毫秒）")
    @ColumnWidth(10)
    private Long spendTime;

    /**
     * 操作用户名称
     */
    @ExcelProperty("操作用户名称")
    @ColumnWidth(12)
    private String operationUsername;

    /**
     * 操作时间
     */
    @ExcelProperty("操作时间")
    @ColumnWidth(20)
    private LocalDateTime operationTime;

}
