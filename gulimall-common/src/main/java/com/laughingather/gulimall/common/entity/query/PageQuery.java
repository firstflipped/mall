package com.laughingather.gulimall.common.entity.query;

import lombok.Data;

/**
 * 分页实体类
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Data
public class PageQuery {

    /**
     * 设置默认分页为第一页
     */
    private Long pn = 1L;

    /**
     * 设置默认每页条数为十条
     */
    private Long ps = 10L;

}
