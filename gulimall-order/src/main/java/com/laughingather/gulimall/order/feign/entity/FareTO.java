package com.laughingather.gulimall.order.feign.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 运费视图展示类
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Data
public class FareTO {

    /**
     * 收获地址信息
     */
    private MemberReceiveAddressTO address;

    /**
     * 运费信息
     */
    private BigDecimal fare;

}

