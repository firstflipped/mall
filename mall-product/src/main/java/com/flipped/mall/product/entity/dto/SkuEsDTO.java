package com.flipped.mall.product.entity.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * skuES服务传输实体
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Data
public class SkuEsDTO {

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
