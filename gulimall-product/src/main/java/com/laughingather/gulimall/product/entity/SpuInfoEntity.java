package com.laughingather.gulimall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * spu信息实体
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Data
@TableName("pms_spu_info")
public class SpuInfoEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 商品id
     */
    @TableId
    private Long id;

    /**
     * 商品名称
     */
    private String spuName;

    /**
     * 上架状态[0 - 新建，1 - 已上架，2 - 已下架]
     */
    private Integer publishStatus;

    /**
     * 所属分类id
     */
    private Long categoryId;

    /**
     * 品牌id
     */
    private Long brandId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
