package com.laughingather.gulimall.coupon.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商品阶梯价格
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-12 11:49:52
 */
@Data
@Builder
@TableName("sms_sku_ladder")
public class SkuLadderEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;

    /**
	 * spu_id
	 */
	private Long skuId;

    /**
	 * 满几件
	 */
	private Integer fullCount;

    /**
	 * 打几折
	 */
	private BigDecimal discount;

    /**
	 * 折后价
	 */
	private BigDecimal price;

    /**
	 * 是否叠加其他优惠[0-不可叠加，1-可叠加]
	 */
	private Integer addOther;

}
