package com.laughingather.gulimall.product.entity.to;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class SkuEsTO {

    private Long skuId;
    private Long spuId;

    /**
     * 标题
     */
    private String skuTitle;

    /**
     * 价格
     */
    private BigDecimal skuPrice;

    /**
     * 图片
     */
    private String skuImg;
    private Long saleCount;
    private Boolean hasStock;
    private Long hotScore;

    /**
     * 关联品牌和分类信息
     */
    private Long brandId;
    private Long categoryId;
    private String brandName;
    private String brandImg;
    private String categoryName;

    private List<Attr> attrs;

    @Data
    public static class Attr {
        private Long attrId;
        private String attrName;
        private String attrValue;
    }

}
