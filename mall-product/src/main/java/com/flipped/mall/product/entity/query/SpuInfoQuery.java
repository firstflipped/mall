package com.flipped.mall.product.entity.query;

import com.flipped.mall.common.entity.query.PageQuery;
import lombok.Data;

/**
 * spu列表查询条件实体
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Data
public class SpuInfoQuery extends PageQuery {

    /**
     * 关键字
     */
    private String key;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 品牌id
     */
    private Long brandId;

    /**
     * 分类id
     */
    private Long categoryId;
}
