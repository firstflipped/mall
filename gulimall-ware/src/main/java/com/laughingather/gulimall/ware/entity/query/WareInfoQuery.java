package com.laughingather.gulimall.ware.entity.query;

import com.laughingather.gulimall.common.query.PageQuery;
import lombok.Data;

/**
 * 仓库查询条件
 *
 * @author laughingather
 */
@Data
public class WareInfoQuery extends PageQuery {
    private String key;
}
