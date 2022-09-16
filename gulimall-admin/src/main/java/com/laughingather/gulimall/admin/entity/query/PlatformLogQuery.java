package com.laughingather.gulimall.admin.entity.query;

import com.laughingather.gulimall.common.entity.query.PageQuery;
import lombok.Data;

/**
 * 平台日志列表查询条件
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-24 21:35:34
 */
@Data
public class PlatformLogQuery extends PageQuery {

    /**
     * 是否是登录
     */
    private Integer login;

    /**
     * 是否成功
     */
    private Integer success;

    /**
     * 操作人id 操作人名称
     */
    private String operation;

}
