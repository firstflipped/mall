package com.laughingather.gulimall.product.entity.vo;

import lombok.Data;

import java.util.List;

/**
 * @author WangJie
 */
@Data
public class SpuItemGroupAttrVO {
    private String groupName;
    private List<SpuBaseAttrVO> attrs;
}