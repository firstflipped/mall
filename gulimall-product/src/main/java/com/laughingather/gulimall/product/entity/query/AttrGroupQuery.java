package com.laughingather.gulimall.product.entity.query;

import com.laughingather.gulimall.common.query.PageQuery;
import lombok.Data;

/**
 * 属性分组列表查询条件实体
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Data
public class AttrGroupQuery extends PageQuery {

    private String key;

}
