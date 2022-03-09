package com.laughingather.gulimall.product.entity.query;

import com.laughingather.gulimall.common.query.PageQuery;
import lombok.Data;

/**
 * spu列表查询条件实体
 *
 * @author laughingather
 */
@Data
public class SpuInfoQuery extends PageQuery {

    /**
     * 状态
     */
    private Integer status;

    /**
     * 关键字
     */
    private String key;

    /**
     * 品牌id
     */
    private Long brandId;

    /**
     * 分类id
     */
    private Long categoryId;
}
