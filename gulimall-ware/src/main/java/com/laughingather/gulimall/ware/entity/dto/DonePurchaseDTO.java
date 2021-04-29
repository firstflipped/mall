package com.laughingather.gulimall.ware.entity.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class DonePurchaseDTO {
    @NotNull
    private Long purchaseId;

    private List<DonePurchaseItemDTO> items;
}
