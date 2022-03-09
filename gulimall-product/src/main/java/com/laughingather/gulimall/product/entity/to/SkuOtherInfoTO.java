package com.laughingather.gulimall.product.entity.to;

import com.laughingather.gulimall.product.entity.param.SkuMemberPriceParam;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * sku信息服务传输实体
 *
 * @author：laughingather
 * @create：2021-06-08 21:18
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SkuOtherInfoTO {

    private Long skuId;
    private Integer fullCount;
    private BigDecimal discount;
    private Integer countStatus;
    private BigDecimal fullPrice;
    private BigDecimal reducePrice;
    private Integer priceStatus;
    private List<SkuMemberPriceParam> memberPrice;
}
