package com.laughingather.gulimall.ware.entity.param;

import lombok.Data;

import java.util.List;

/**
 * 合并采购单前端传入类
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Data
public class MergePurchaseParam {

    private Long purchaseId;

    private List<Long> items;
}
