package com.laughingather.gulimall.ware.entity.vo;

import com.laughingather.gulimall.ware.feign.entity.MemberReceiveAddressDTO;
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
public class FareVO {

    /**
     * 收货地址信息
     */
    private MemberReceiveAddressDTO address;

    /**
     * 运费
     */
    private BigDecimal fare;

}

