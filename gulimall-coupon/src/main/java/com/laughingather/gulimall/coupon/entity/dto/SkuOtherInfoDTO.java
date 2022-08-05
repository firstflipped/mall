package com.laughingather.gulimall.coupon.entity.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * sku信息传输类
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Data
public class SkuOtherInfoDTO {
    private Long skuId;
    private Integer fullCount;
    private BigDecimal discount;
    private Integer countStatus;
    private BigDecimal fullPrice;
    private BigDecimal reducePrice;
    private Integer priceStatus;
    private List<MemberPriceDTO> memberPriceDTO;
}
