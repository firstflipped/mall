package com.laughingather.gulimall.product.entity.query;

import com.laughingather.gulimall.common.query.PageQuery;
import lombok.Data;

@Data
public class SpuInfoQuery extends PageQuery {
    private Integer status;
    private String key;
    private Long brandId;
    private Long catalogId;
}
