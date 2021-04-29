package com.laughingather.gulimall.ware.entity.query;

import com.laughingather.gulimall.common.query.PageQuery;
import lombok.Data;

@Data
public class PurchaseDetailQuery extends PageQuery {

    private String key;
    private Integer status;
    private Long wareId;

}
