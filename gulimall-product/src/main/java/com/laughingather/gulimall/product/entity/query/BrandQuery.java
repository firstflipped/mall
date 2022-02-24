package com.laughingather.gulimall.product.entity.query;

import com.laughingather.gulimall.common.query.PageQuery;
import lombok.Data;

/**
 * 品牌列表条件搜索
 *
 * @author laughingather
 */
@Data
public class BrandQuery extends PageQuery {

    private String name;

}
