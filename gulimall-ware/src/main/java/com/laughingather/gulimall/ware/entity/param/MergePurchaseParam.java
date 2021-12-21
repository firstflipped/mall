package com.laughingather.gulimall.ware.entity.param;

import lombok.Data;

import java.util.List;

/**
 * 合并采购单前端传入类
 *
 * @author laughingather
 */
@Data
public class MergePurchaseParam {

    private Long purchaseId;

    private List<Long> items;
}
