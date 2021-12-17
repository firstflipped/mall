package com.laughingather.gulimall.order.feign.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 运费视图展示类
 *
 * @author：laughingather
 * @create：2021-10-19 2021/10/19
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

