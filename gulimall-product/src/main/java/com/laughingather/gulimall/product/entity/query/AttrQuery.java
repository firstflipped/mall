package com.laughingather.gulimall.product.entity.query;

import com.laughingather.gulimall.common.query.PageQuery;
import lombok.Data;

/**
 * 属性列表查询条件实体
 *
 * @author laughingather
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