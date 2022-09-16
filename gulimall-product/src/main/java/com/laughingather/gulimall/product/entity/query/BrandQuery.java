package com.laughingather.gulimall.product.entity.query;

import com.laughingather.gulimall.common.entity.query.PageQuery;
import lombok.Data;

import javax.validation.constraints.Pattern;

/**
 * 品牌列表查询条件实体
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Data
public class BrandQuery extends PageQuery {

    /**
     * 品牌名称
     */
    private String name;

    /**
     * 排序规则
     * 排序字段-排序类型：sort-desc
     */
    @Pattern(regexp = "^sort-(asc|desc)$", message = "排序规则不规范")
    private String sort;

}
