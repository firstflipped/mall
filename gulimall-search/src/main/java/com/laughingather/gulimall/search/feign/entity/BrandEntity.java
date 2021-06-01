package com.laughingather.gulimall.search.feign.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 品牌
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-11 15:12:49
 */
@Data
public class BrandEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 品牌id
	 */
	private Long brandId;
	/**
	 * 品牌名
	 */
	private String name;
	/**
	 * 品牌logo地址
	 */
	private String logo;
	/**
	 * 介绍
	 */
	private String descript;
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

}
