package com.laughingather.gulimall.search.feign.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 品牌
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Data
public class BrandTO {

    /**
     * 品牌id
     */
    private Long brandId;

    /**
     * 品牌名
     */
    private String brandName;

    /**
     * 品牌logo地址
     */
    private String logo;

    /**
     * 介绍
     */
    private String description;

    /**
     * 显示状态[0-不显示；1-显示]
     */
    private Integer showStatus;

    /**
     * 检索首字母
     */
    private String firstLetter;

    /**
     * 排序
     */
    private Integer sort;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;


}
