package com.laughingather.gulimall.product.entity.query;

import com.laughingather.gulimall.common.query.PageQuery;
import lombok.Data;

/**
 * 属性分组条件查询参数
 *
 * @author laughingather
 */
@Data
public class AttrGroupQuery extends PageQuery {

    private String key;

}
