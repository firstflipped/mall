package com.laughingather.gulimall.ware.entity.dto;

import lombok.Data;

@Data
public class DonePurchaseItemDTO {
    private Long itemId;
    private Integer status;
    private String reason;

}
