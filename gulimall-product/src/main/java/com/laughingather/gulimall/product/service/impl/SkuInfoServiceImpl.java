package com.laughingather.gulimall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.product.dao.SkuInfoDao;
import com.laughingather.gulimall.product.entity.SkuImagesEntity;
import com.laughingather.gulimall.product.entity.SkuInfoEntity;
import com.laughingather.gulimall.product.entity.SpuInfoDescEntity;
import com.laughingather.gulimall.product.entity.query.SkuInfoQuery;
import com.laughingather.gulimall.product.entity.vo.ItemSaleAttrVO;
import com.laughingather.gulimall.product.entity.vo.SkuItemVO;
import com.laughingather.gulimall.product.entity.vo.SpuItemGroupAttrVO;
import com.laughingather.gulimall.product.service.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service("skuInfoService")
public class SkuInfoServiceImpl extends ServiceImpl<SkuInfoDao, SkuInfoEntity> implements SkuInfoService {

    @Resource
    private SkuInfoDao skuInfoDao;
    @Resource
    private SkuImagesService skuImagesService;
    @Resource
    private SpuInfoDescService spuInfoDescService;
    @Resource
    private AttrGroupService attrGroupService;
    @Resource
    private SkuSaleAttrValueService skuSaleAttrValueService;

    @Override
    public MyPage<SkuInfoEntity> pageSkuInfoByParams(SkuInfoQuery skuInfoQuery) {
        IPage<SkuInfoEntity> page = new Page<>(skuInfoQuery.getPageNumber(), skuInfoQuery.getPageSize());
        IPage<SkuInfoEntity> skuInfoIPage = skuInfoDao.pageSkuInfoByParams(page, skuInfoQuery);
        return MyPage.restPage(skuInfoIPage);
    }

    @Override
    public List<SkuInfoEntity> listSkusBySpuId(Long spuId) {
        return skuInfoDao.selectList(new QueryWrapper<SkuInfoEntity>()
                .lambda().eq(SkuInfoEntity::getSpuId, spuId));
    }

    @Override
    public SkuItemVO getSkuItemById(Long skuId) {
        SkuItemVO skuItemVO = new SkuItemVO();
        // 查询sku基本信息
        SkuInfoEntity skuInfo = getById(skuId);
        skuItemVO.setInfo(skuInfo);

        Long catalogId = skuInfo.getCatalogId();
        Long spuId = skuInfo.getSpuId();

        // 查询sku的图片信息
        List<SkuImagesEntity> skuImages = skuImagesService.listImagesBySkuId(skuId);
        skuItemVO.setImages(skuImages);

        // 当前spu的销售属性组合
        List<ItemSaleAttrVO> saleAttrs = skuSaleAttrValueService.getSaleAttrsBySpuId(spuId);
        skuItemVO.setSaleAttrs(saleAttrs);

        // 获取spu的描述
        SpuInfoDescEntity spuInfoDesc = spuInfoDescService.getById(skuInfo.getSpuId());
        skuItemVO.setDesc(spuInfoDesc);

        // 获取sku的规格参数信息
        List<SpuItemGroupAttrVO> groupAttrVO = attrGroupService.getAttrGroupWithAttrsBySpuId(catalogId, spuId);
        skuItemVO.setGroupAttrs(groupAttrVO);

        return skuItemVO;
    }
}