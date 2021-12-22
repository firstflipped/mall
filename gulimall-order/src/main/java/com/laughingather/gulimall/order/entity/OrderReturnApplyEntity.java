package com.laughingather.gulimall.order.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 订单退货申请
 * 
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-12 11:17:55
 */
@Data
@TableName("oms_order_return_apply")
public class OrderReturnApplyEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;

	/**
	 * order_id
	 */
	private Long orderId;

	/**
	 * 退货商品id
	 */
	private Long skuId;

    /**
	 * 订单编号
	 */
	private String orderSn;

    /**
	 * 申请时间
	 */
	private LocalDateTime createTime;

    /**
	 * 会员用户名
	 */
	private String memberUsername;

    /**
	 * 退款金额
	 */
	private BigDecimal returnAmount;

    /**
	 * 退货人姓名
	 */
	private String returnName;

    /**
	 * 退货人电话
	 */
	private String returnPhone;

    /**
	 * 申请状态[0->待处理；1->退货中；2->已完成；3->已拒绝]
	 */
	private Integer status;

    /**
     * 处理时间
     */
	private LocalDateTime handleTime;

    /**
	 * 商品图片
	 */
	private String skuImg;

    /**
	 * 商品名称
	 */
    private String skuName;

    /**
     * 商品品牌
     */
    private String skuBrand;

    /**
     * 商品销售属性(JSON)
     */
    private String skuAttrsValues;

    /**
     * 退货数量
     */
    private Integer skuCount;

    /**
     * 商品单价
     */
    private BigDecimal skuPrice;

    /**
     * 商品实际支付单价
     */
    private BigDecimal skuRealPrice;

    /**
     * 原因
     */
    private Long reasonId;

    /**
     * 描述
     */
    private String description;

    /**
     * 凭证图片，以逗号隔开
     */
    private String descPics;

    /**
     * 处理备注
     */
    private String handleNote;

	/**
	 * 处理人员
	 */
	private String handleMan;

	/**
	 * 收货人
	 */
	private String receiveMan;

    /**
	 * 收货时间
	 */
	private LocalDateTime receiveTime;

	/**
	 * 收货备注
	 */
	private String receiveNote;

	/**
	 * 收货电话
	 */
	private String receivePhone;

	/**
	 * 公司收货地址
	 */
	private String companyAddress;

}
