package com.flipped.mall.ware.entity.query;

import com.flipped.mall.common.entity.query.PageQuery;
import lombok.Data;

/**
 * 采购单详情查询条件
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Data
public class PurchaseDetailQuery extends PageQuery {

    private String key;
    private Integer status;
    private Long wareId;

}
