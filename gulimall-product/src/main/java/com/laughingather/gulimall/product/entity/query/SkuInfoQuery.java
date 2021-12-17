package com.laughingather.gulimall.product.entity.query;

import com.laughingather.gulimall.common.query.PageQuery;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class SkuInfoQuery extends PageQuery {

    private String key;

    private Long brandId;

    private Long categoryId;

    /**
     * 价格区间，最低价
     */
    private BigDecimal min;

    /**
     * 价格区间，最高价
     */
    private BigDecimal max;
}
