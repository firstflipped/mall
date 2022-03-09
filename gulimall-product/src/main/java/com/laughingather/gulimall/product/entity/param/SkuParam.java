package com.laughingather.gulimall.product.entity.param;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * sku传入实体
 *
 * @author laughingather
 */
@Data
public class SkuParam {

    /**
     * sku名称
     */
    private String skuName;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 标题
     */
    private String skuTitle;

    /**
     * 副标题
     */
    private String skuSubtitle;

    /**
     * 优惠
     */
    private Integer fullCount;
    private BigDecimal discount;
    private Integer countStatus;
    private BigDecimal fullPrice;
    private BigDecimal reducePrice;
    private Integer priceStatus;

    /**
     * 会员价格
     */
    private List<SkuMemberPriceParam> memberPrice;

    /**
     * 图片集
     */
    private List<SkuImageParam> images;

    private List<String> description;

    /**
     * 属性集合
     */
    private List<SkuAttrParam> attr;
}