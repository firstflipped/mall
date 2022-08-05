package com.laughingather.gulimall.product.entity.vo;

import com.laughingather.gulimall.product.entity.SkuImagesEntity;
import com.laughingather.gulimall.product.entity.SkuInfoEntity;
import com.laughingather.gulimall.product.entity.SpuInfoDescEntity;
import com.laughingather.gulimall.product.feign.entity.SecKillSkuRedisDTO;
import lombok.Data;

import java.util.List;

/**
 * 商品详情视图展示实体
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Data
public class SkuItemVO {

    /**
     * 基本信息
     */
    private SkuInfoEntity info;

    /**
     * 是否有货
     */
    private Boolean hasStock;

    /**
     * 图片
     */
    private List<SkuImagesEntity> images;

    /**
     * 销售属性
     */
    private List<ItemSaleAttrVO> saleAttrs;

    /**
     * 商品介绍
     */
    private SpuInfoDescEntity desc;

    /**
     * 规格参数信息
     */
    private List<SpuItemAttrGroupWithAttrVO> groupAttrs;

    /**
     * 秒杀信息
     */
    private SecKillSkuRedisDTO secKill;

}
