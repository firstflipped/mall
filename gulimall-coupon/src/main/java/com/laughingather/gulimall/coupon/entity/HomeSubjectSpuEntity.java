package com.laughingather.gulimall.coupon.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 专题商品
 * 
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-12 11:49:52
 */
@Data
@TableName("sms_home_subject_spu")
public class HomeSubjectSpuEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;

    /**
	 * 专题名字
	 */
	private String name;

    /**
	 * 专题id
	 */
	private Long subjectId;

    /**
	 * spu_id
	 */
	private Long spuId;

    /**
	 * 排序
	 */
	private Integer sort;

}
