/**
 * Copyright 2021 json.cn
 */
package com.laughingather.gulimall.product.entity.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * Auto-generated: 2021-04-28 12:2:54
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
@Data
public class SpuSaveDTO {
    private String spuName;
    private String spuDescription;
    private Long catalogId;
    private Long brandId;
    private BigDecimal weight;
    private Integer publishStatus;

    private List<String> decript;
    private List<String> images;
    private Bound bounds;
    private List<BaseAttr> baseAttrs;
    private List<Sku> skus;
}