package com.laughingather.gulimall.order.entity.vo;

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
public class OrderItemVO {

    /**
     * skuId
     */
    private Long skuId;

    /**
     * 是否被选中
     */
    private Boolean check;

    /**
     * 标题
     */
    private String title;

    /**
     * 图片地址
     */
    private String image;

    /**
     * 销售属性
     */
    private List<String> skuAttr;

    /**
     * 单价
     */
    private BigDecimal price;

    /**
     * 数量
     */
    private Integer count;

    /**
     * 总价
     */
    private BigDecimal totalPrice;

    /**
     * 是否有货
     */
    private Boolean hasStock = true;

    /**
     * 商品重量
     */
    private BigDecimal weight;

}