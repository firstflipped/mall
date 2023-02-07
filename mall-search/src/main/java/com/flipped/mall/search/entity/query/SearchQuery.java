package com.flipped.mall.search.entity.query;

import lombok.Data;

import java.util.List;

/**
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Data
public class SearchQuery {

    private String keyword;

    /**
     * 分类id
     */
    private Long categoryId;

    /**
     * 排序条件
     */
    private String sort;

    /**
     * 是否只显示有货
     */
    private Integer hasStock;

    /**
     * 价格区间
     * 1-500  -500  100-
     */
    private String skuPrice;

    /**
     * 品牌 可以多选
     */
    private List<Long> brandId;

    /**
     * 属性 可以多选
     * 1_A13
     */
    private List<String> attrs;

    /**
     * 页码
     */
    private Integer pageNum;

    /**
     * 原始url
     */
    private String _queryUrl;

}
