package com.laughingather.gulimall.ware.entity.param;

import lombok.Data;

import java.util.List;

/**
 * @author laughingather
 */
@Data
public class DonePurchaseParam {

    /**
     * 采购单id
     */
    private Long purchaseId;

    private List<DonePurchaseItemParam> items;
}
