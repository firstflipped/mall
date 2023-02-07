package com.flipped.mall.admin.entity.vo;

import lombok.Data;

/**
 * 字典下拉选择器视图
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2023-02-06 10:07:16
 */
@Data
public class DictSelectVO {

    /**
     * 字典id
     */
    private Long dictId;

    /**
     * 字典编码
     */
    private String dictCode;

    /**
     * 字典名称
     */
    private String dictName;

}
