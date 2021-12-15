package com.laughingather.gulimall.product.entity.query;

import com.laughingather.gulimall.common.query.PageQuery;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class SkuInfoQuery extends PageQuery {

    private String key;

    private Long brandId;

    private Long categoryId;

    private BigDecimal min;

    private BigDecimal max;
}
