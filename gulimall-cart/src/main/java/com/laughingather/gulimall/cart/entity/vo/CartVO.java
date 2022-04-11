package com.laughingather.gulimall.cart.entity.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 购物车视图展示类
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Data
public class CartVO {

    private List<CartItemVO> items;

    /**
     * 商品数量
     */
    private Integer countNum;

    /**
     * 商品类型数量
     */
    private Integer countType;

    /**
     * 商品总价
     */
    private BigDecimal totalAmount;

    /**
     * 减免价格
     */
    private BigDecimal reduce;

}

