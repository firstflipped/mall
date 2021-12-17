package com.laughingather.gulimall.product.entity.query;

import com.laughingather.gulimall.common.query.PageQuery;
import lombok.Data;

@Data
public class BrandQuery extends PageQuery {

    private String brandName;

}
