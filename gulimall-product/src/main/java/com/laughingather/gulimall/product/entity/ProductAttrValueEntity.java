package com.laughingather.gulimall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * spu属性值实体
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-11 15:12:49
 */
@Data
@Builder
@TableName("pms_product_attr_value")
public class ProductAttrValueEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;

	/**
	 * 商品id
	 */
	private Long spuId;

	/**
	 * 属性id
	 */
	private Long attrId;

	/**
	 * 属性名
	 */
	private String attrName;

	/**
	 * 属性值
	 */
	private String attrValue;

	/**
	 * 顺序
	 */
	private Integer attrSort;

	/**
	 * 快速展示【是否展示在介绍上；0-否 1-是】
	 */
	private Integer quickShow;

}
