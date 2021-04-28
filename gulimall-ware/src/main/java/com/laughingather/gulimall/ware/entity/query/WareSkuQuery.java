package com.laughingather.gulimall.ware.entity.query;

import com.laughingather.gulimall.common.query.PageQuery;
import lombok.Data;

@Data
public class WareSkuQuery extends PageQuery {
    private Long skuId;
    private Long wareId;
}
