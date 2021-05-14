package com.laughingather.gulimall.product.entity.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class Sku {

    private String skuName;
    private BigDecimal price;
    private String skuTitle;
    private String skuSubtitle;
    private Integer fullCount;
    private BigDecimal discount;
    private Integer countStatus;
    private BigDecimal fullPrice;
    private BigDecimal reducePrice;
    private Integer priceStatus;
    private List<MemberPrice> memberPrice;

    private List<Image> images;
    private List<String> descar;
    private List<Attr> attrs;
}