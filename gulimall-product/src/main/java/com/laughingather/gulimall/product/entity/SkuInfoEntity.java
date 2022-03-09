package com.laughingather.gulimall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * sku信息实体
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-11 15:12:49
 */
@Data
@TableName("pms_sku_info")
public class SkuInfoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * skuId
	 */
	@TableId
	private Long skuId;

	/**
	 * sku名称
	 */
	private String skuName;

	/**
	 * sku介绍描述
	 */
	private String skuDesc;

	/**
	 * 默认图片
	 */
	private String skuDefaultImg;

	/**
	 * 标题
	 */
	private String skuTitle;

	/**
	 * 副标题
	 */
	private String skuSubtitle;

	/**
	 * 价格
	 */
	private BigDecimal price;

	/**
	 * 销量
	 */
	private Long saleCount;

	/**
	 * 所属分类id
	 */
	private Long categoryId;

    /**
     * 品牌id
     */
    private Long brandId;

    /**
     * spuId
     */
    private Long spuId;

    /**
     * 创建时间
     */
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime createTime;

}
