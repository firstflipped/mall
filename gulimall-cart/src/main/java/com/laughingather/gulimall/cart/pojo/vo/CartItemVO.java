package com.laughingather.gulimall.cart.pojo.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 购物项视图展示类
 *
 * @author：laughingather
 * @create：2021-08-16 2021/8/16
 */
@Data
public class CartItemVO {

    private Long skuId;

    private Boolean check;

    private String title;

    private String image;

    private List<String> skuAttr;

    private BigDecimal price;

    private Integer count;

    private BigDecimal totalPrice;

}

