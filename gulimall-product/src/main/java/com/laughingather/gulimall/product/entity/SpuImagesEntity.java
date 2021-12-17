package com.laughingather.gulimall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * spu图片
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-11 15:12:49
 */
@Data
@Builder
@TableName("pms_spu_images")
public class SpuImagesEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@TableId
	private Long id;

    /**
	 * spu_id
	 */
	private Long spuId;

    /**
	 * 图片名
	 */
	private String imgName;

    /**
	 * 图片地址
	 */
	private String imgUrl;

    /**
	 * 顺序
	 */
	private Integer imgSort;

    /**
	 * 是否默认图
	 */
	private Integer defaultImg;

}
