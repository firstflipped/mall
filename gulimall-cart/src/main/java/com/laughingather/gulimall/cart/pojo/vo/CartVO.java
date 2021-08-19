package com.laughingather.gulimall.cart.pojo.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 购物车视图展示类
 *
 * @author：laughingather
 * @create：2021-08-16 2021/8/16
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

