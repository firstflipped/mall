package com.laughingather.gulimall.search.entity.query;

import lombok.Data;

import java.util.List;

/**
 * @author WangJie
 */
@Data
public class SearchQuery {

    private String keyword;

    /**
     * 分类id
     */
    private Long catalog3Id;

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

}
