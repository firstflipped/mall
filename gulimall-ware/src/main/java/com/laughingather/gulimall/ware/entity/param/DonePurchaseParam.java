package com.laughingather.gulimall.ware.entity.param;

import lombok.Data;

import java.util.List;

/**
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Data
public class DonePurchaseParam {

    /**
     * 采购单id
     */
    private Long purchaseId;

    private List<DonePurchaseItemParam> items;
}
