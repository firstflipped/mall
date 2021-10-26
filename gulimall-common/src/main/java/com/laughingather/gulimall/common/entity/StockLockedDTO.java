package com.laughingather.gulimall.common.entity;

import lombok.Data;

/**
 * 库存锁定成功实体传输类
 *
 * @author：laughingather
 * @create：2021-10-25 2021/10/25
 */
@Data
public class StockLockedDTO {

    /**
     * 库存工作单的id
     */
    private Long id;

    /**
     * 库存工作单详情id
     */
    private StockDetailDTO detail;

}

