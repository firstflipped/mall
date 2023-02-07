package com.flipped.mall.ware.entity.param;

import lombok.Data;

/**
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
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
