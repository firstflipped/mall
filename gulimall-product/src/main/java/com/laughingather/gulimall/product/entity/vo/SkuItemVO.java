package com.laughingather.gulimall.product.entity.vo;

import com.laughingather.gulimall.product.entity.SkuImagesEntity;
import com.laughingather.gulimall.product.entity.SkuInfoEntity;
import com.laughingather.gulimall.product.entity.SpuInfoDescEntity;
import com.laughingather.gulimall.product.feign.entity.SecKillSkuRedisTO;
import lombok.Data;

import java.util.List;

/**
 * 商品详情视图展示类
 *
 * @author：laughingather
 * @create：2021-06-04 22:16
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
    private List<SpuItemGroupAttrVO> groupAttrs;

    /**
     * 秒杀信息
     */
    private SecKillSkuRedisTO secKill;

}
