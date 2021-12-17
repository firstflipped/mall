package com.laughingather.gulimall.product.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 品牌视图展示类
 *
 * @author：laughingather
 * @create：2021-06-08 21:18
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BrandVO {

    /**
     * 品牌id
     */
    private Long brandId;

    /**
     * 品牌名
     */
    private String brandName;

}
