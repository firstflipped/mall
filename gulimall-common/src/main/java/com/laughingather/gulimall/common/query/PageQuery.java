package com.laughingather.gulimall.common.query;

import lombok.Data;

/**
 * 分页实体类
 *
 * @author laughingather
 */
@Data
public class PageQuery {

    /**
     * 设置默认分页为第一页
     */
    private Integer pn = 1;

    /**
     * 设置默认每页条数为十条
     */
    private Integer ps = 10;

}
