package com.laughingather.gulimall.common.entity;

import lombok.Data;

/**
 * 锁定库存工作单详情
 *
 * @author：laughingather
 * @create：2021-10-26
 */
@Data
public class StockDetailDTO {

    private Long id;

    /**
     * sku_id
     */
    private Long skuId;

    /**
     * 商品名称
     */
    private String skuName;

    /**
     * 购买个数
     */
    private Integer skuNum;

    /**
     * 工作单id
     */
    private Long taskId;

    /**
     * 仓库id
     */
    private Long wareId;

    /**
     * 库存状态
     */
    private Integer lockStatus;

}

