package com.laughingather.gulimall.product.entity.vo;

import com.laughingather.gulimall.product.entity.CategoryEntity;
import lombok.Data;
import lombok.ToString;

import java.util.List;


/**
 * @author WangJie
 */

@Data
@ToString
public class CategoryTreeVO extends CategoryEntity {

    private List<CategoryTreeVO> children;

}
