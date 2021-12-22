package com.laughingather.gulimall.coupon.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 优惠券信息
 * 
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-12 11:49:53
 */
@Data
@TableName("sms_coupon")
public class CouponEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;

	/**
	 * 优惠卷类型[0->全场赠券；1->会员赠券；2->购物赠券；3->注册赠券]
	 */
	private Integer couponType;

    /**
	 * 优惠券图片
	 */
	private String couponImg;

    /**
     * 优惠券名字
     */
	private String couponName;

	/**
	 * 数量
	 */
	private Integer num;

	/**
	 * 金额
	 */
	private BigDecimal amount;

	/**
	 * 每人限领张数
	 */
	private Integer perLimit;

    /**
     * 使用门槛
     */
    private BigDecimal minPoint;

    /**
     * 开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime endTime;

    /**
     * 使用类型[0->全场通用；1->指定分类；2->指定商品]
     */
    private Integer useType;

	/**
	 * 备注
	 */
	private String note;

	/**
	 * 发行数量
	 */
	private Integer publishCount;

	/**
	 * 已使用数量
	 */
	private Integer useCount;

    /**
     * 领取数量
     */
    private Integer receiveCount;

    /**
     * 可以领取的开始日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime enableStartTime;

    /**
     * 可以领取的结束日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime enableEndTime;

    /**
     * 优惠码
     */
	private String code;

	/**
	 * 可以领取的会员等级[0->不限等级，其他-对应等级]
	 */
	private Integer memberLevel;

	/**
	 * 发布状态[0-未发布，1-已发布]
	 */
	private Integer publish;

}
