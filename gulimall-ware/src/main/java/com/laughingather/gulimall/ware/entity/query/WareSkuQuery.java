package com.laughingather.gulimall.ware.entity.query;

import com.laughingather.gulimall.common.query.PageQuery;
import lombok.Data;

/**
 * 商品仓库查询条件
 *
 * @author laughingather
 */
@Data
public class WareSkuQuery extends PageQuery {

    /**
     * 商品id
     */
    private Long skuId;

    /**
     * 仓库id
     */
    private Long wareId;
}
