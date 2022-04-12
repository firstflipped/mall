package com.laughingather.gulimall.product.entity.param;

import lombok.Data;

/**
 * sku图片输入实体
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Data
public class SkuImageParam {
    /**
     * 图片地址
     */
    private String imgUrl;

    /**
     * 是否是默认图片
     */
    private Integer defaultImg;
}