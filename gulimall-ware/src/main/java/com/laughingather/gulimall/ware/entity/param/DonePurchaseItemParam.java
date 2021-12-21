package com.laughingather.gulimall.ware.entity.param;

import lombok.Data;

/**
 * @author laughingather
 */
@Data
public class DonePurchaseItemParam {
    /**
     * 商品id
     */
    private Long itemId;

    /**
     * 状态
     */
    private Integer status;

    private String reason;

}
