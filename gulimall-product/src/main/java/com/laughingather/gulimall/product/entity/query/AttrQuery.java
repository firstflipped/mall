package com.laughingather.gulimall.product.entity.query;

import com.laughingather.gulimall.common.entity.query.PageQuery;
import lombok.Data;

/**
 * 属性列表查询条件实体
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Data
public class AttrQuery extends PageQuery {

    /**
     * 关键字
     */
    private String key;

    /**
     * 属性类型
     */
    private Integer type;

}