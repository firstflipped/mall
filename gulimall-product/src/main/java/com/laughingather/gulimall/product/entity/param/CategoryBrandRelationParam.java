package com.laughingather.gulimall.product.entity.param;

import com.laughingather.gulimall.common.valid.AddGroup;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CategoryBrandRelationParam {
    /**
     * 品牌id
     */
    @NotNull(message = "品牌id不能为空", groups = {AddGroup.class})
    private Long brandId;

    /**
     * 分类id
     */
    @NotNull(message = "分类id不能为空", groups = {AddGroup.class})
    private Long categoryId;
}