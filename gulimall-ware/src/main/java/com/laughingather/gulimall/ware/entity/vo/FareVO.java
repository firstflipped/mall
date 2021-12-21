package com.laughingather.gulimall.ware.entity.vo;

import com.laughingather.gulimall.ware.feign.entity.MemberReceiveAddressTO;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 运费视图展示类
 *
 * @author：laughingather
 * @create：2021-10-19 2021/10/19
 */
@Data
public class FareVO {

    /**
     * 收货地址信息
     */
    private MemberReceiveAddressTO address;

    /**
     * 运费
     */
    private BigDecimal fare;

}

