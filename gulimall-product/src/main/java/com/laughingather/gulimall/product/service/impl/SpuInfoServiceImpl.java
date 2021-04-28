package com.laughingather.gulimall.product.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.common.constant.ProductConstants;
import com.laughingather.gulimall.product.dao.SpuInfoDao;
import com.laughingather.gulimall.product.entity.*;
import com.laughingather.gulimall.product.entity.dto.*;
import com.laughingather.gulimall.product.entity.query.SpuInfoQuery;
import com.laughingather.gulimall.product.entity.to.SkuOtherInfoTO;
import com.laughingather.gulimall.product.entity.to.SpuBoundTO;
import com.laughingather.gulimall.product.feign.service.CouponFeignService;
import com.laughingather.gulimall.product.service.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service("spuInfoService")
public class SpuInfoServiceImpl extends ServiceImpl<SpuInfoDao, SpuInfoEntity> implements SpuInfoService {


    @Resource
    private SpuInfoDao spuInfoDao;

    @Autowired
    private SpuInfoDescService spuInfoDescService;
    @Autowired
    private SpuImagesService spuImagesService;
    @Autowired
    private AttrService attrService;
    @Autowired
    private ProductAttrValueService productAttrValueService;
    @Autowired
    private SkuInfoService skuInfoService;
    @Autowired
    private SkuImagesService skuImagesService;
    @Autowired
    private SkuSaleAttrValueService skuSaleAttrValueService;
    @Autowired
    private CouponFeignService couponFeignService;

    @Override
    public MyPage<SpuInfoEntity> pageSpuInfoByParams(SpuInfoQuery spuInfoQuery) {
        IPage<SpuInfoEntity> page = new Page<>(spuInfoQuery.getPageNumber(), spuInfoQuery.getPageSize());
        IPage<SpuInfoEntity> spuInfoEntityIPage = spuInfoDao.pageSpuInfoByParams(page, spuInfoQuery);
        return MyPage.restPage(spuInfoEntityIPage);
    }


    @Override
    @Transactional
    public void saveSpuInfo(SpuSaveDTO spuSaveDTO) {
        // 保存spu基本信息
        SpuInfoEntity spuInfo = saveSpuBaseInfo(spuSaveDTO);
        Long spuInfoId = spuInfo.getId();

        // 保存spu的描述图片
        saveSpuInfoDesc(spuInfoId, spuSaveDTO.getDecript());

        // 保存spu的图片集
        saveSpuImages(spuInfoId, spuSaveDTO.getImages());

        // 保存spu的规格参数
        saveSpuAttrs(spuInfoId, spuSaveDTO.getBaseAttrs());

        // 保存spu的积分信息
        saveSpuBounds(spuInfoId, spuSaveDTO.getBounds());

        // 保存spu对应的所有sku信息
        saveSkus(spuInfo, spuSaveDTO.getSkus());
    }

    private SpuInfoEntity saveSpuBaseInfo(SpuSaveDTO spuSaveDTO) {
        SpuInfoEntity spuInfo = new SpuInfoEntity();
        BeanUtils.copyProperties(spuSaveDTO, spuInfo);
        // 设置创建时间
        spuInfo.setCreateTime(LocalDateTime.now());
        this.save(spuInfo);

        return spuInfo;
    }

    private void saveSpuInfoDesc(Long spuInfoId, List<String> descList) {
        String desc = String.join(",", descList);
        SpuInfoDescEntity spuInfoDesc = SpuInfoDescEntity.builder().spuId(spuInfoId).decript(desc).build();
        spuInfoDescService.save(spuInfoDesc);
    }

    private void saveSpuImages(Long spuInfoId, List<String> images) {
        if (CollectionUtils.isNotEmpty(images)) {
            List<SpuImagesEntity> spuImages = images.stream().map(image ->
                    SpuImagesEntity.builder().spuId(spuInfoId).imgUrl(image).build()
            ).collect(Collectors.toList());
            spuImagesService.saveBatch(spuImages);
        }
    }

    private void saveSpuAttrs(Long spuInfoId, List<BaseAttr> baseAttrs) {
        if (CollectionUtils.isNotEmpty(baseAttrs)) {
            List<ProductAttrValueEntity> productAttrValues = baseAttrs.stream().map(attr -> {
                // 获取属性名
                AttrEntity byId = attrService.getById(attr.getAttrId());
                return ProductAttrValueEntity.builder().spuId(spuInfoId).attrId(attr.getAttrId())
                        .attrName(byId == null ? "" : byId.getAttrName()).attrValue(attr.getAttrValues())
                        .quickShow(attr.getShowDesc()).build();
            }).collect(Collectors.toList());

            productAttrValueService.saveBatch(productAttrValues);
        }
    }

    private void saveSpuBounds(Long spuInfoId, Bound bound) {
        SpuBoundTO spuBoundTO = SpuBoundTO.builder().spuId(spuInfoId)
                .buyBounds(bound.getBuyBounds()).growBounds(bound.getGrowBounds()).build();
        Boolean isSuccess = couponFeignService.saveSpuBounds(spuBoundTO);
        if (!isSuccess) {
            log.error("远程调用服务失败，保存spu的积分信息");
        }
    }

    private void saveSkus(SpuInfoEntity spuInfo, List<Sku> skus) {
        if (CollectionUtils.isNotEmpty(skus)) {
            skus.stream().forEach(sku -> {
                // 保存sku基本信息
                SkuInfoEntity skuInfo = saveSkuInfo(spuInfo, sku);
                Long skuId = skuInfo.getSkuId();

                // 保存sku图片信息
                List<Image> images = sku.getImages();
                saveSkuImages(skuId, images);

                // 保存sku销售属性信息
                List<Attr> attrs = sku.getAttr();
                saveSkuAttrs(skuId, attrs);

                // 保存sku优惠、满减等信息
                saveSkuOther(skuId, sku);
            });
        }
    }

    private SkuInfoEntity saveSkuInfo(SpuInfoEntity spuInfo, Sku sku) {
        // 默认图片
        String defaultImg = "";
        for (Image image : sku.getImages()) {
            if (ProductConstants.AttrEnum.DEFAULT_IMAGE.getCode().equals(image.getDefaultImg())) {
                defaultImg = image.getImgUrl();
            }
        }

        SkuInfoEntity skuInfo = new SkuInfoEntity();
        BeanUtils.copyProperties(sku, skuInfo);
        skuInfo.setSpuId(spuInfo.getId());
        skuInfo.setBrandId(spuInfo.getBrandId());
        skuInfo.setCatalogId(spuInfo.getCatalogId());
        skuInfo.setSkuDefaultImg(defaultImg);
        skuInfo.setSaleCount(0L);

        skuInfoService.save(skuInfo);
        return skuInfo;
    }

    private void saveSkuImages(Long skuId, List<Image> images) {
        if (CollectionUtils.isNotEmpty(images)) {
            List<SkuImagesEntity> skuImages = images.stream().map(image ->
                    SkuImagesEntity.builder().skuId(skuId).imgUrl(image.getImgUrl())
                            .defaultImg(image.getDefaultImg()).build()
            ).filter(skuImage ->
                    StringUtils.isNoneBlank(skuImage.getImgUrl())
            ).collect(Collectors.toList());
            skuImagesService.saveBatch(skuImages);
        }
    }

    private void saveSkuAttrs(Long skuId, List<Attr> attrs) {
        if (CollectionUtils.isNotEmpty(attrs)) {
            List<SkuSaleAttrValueEntity> skuAttrs = attrs.stream().map(attr -> {
                SkuSaleAttrValueEntity skuAttr = new SkuSaleAttrValueEntity();
                BeanUtils.copyProperties(attr, skuAttr);
                skuAttr.setSkuId(skuId);
                return skuAttr;
            }).collect(Collectors.toList());
            skuSaleAttrValueService.saveBatch(skuAttrs);
        }
    }

    private void saveSkuOther(Long skuId, Sku sku) {
        SkuOtherInfoTO skuOtherInfoTO = new SkuOtherInfoTO();
        BeanUtils.copyProperties(sku, skuOtherInfoTO);
        skuOtherInfoTO.setSkuId(skuId);
        if (skuOtherInfoTO.getFullCount() > 0 || (skuOtherInfoTO.getFullPrice().compareTo(BigDecimal.ZERO) == 1)) {
            Boolean isSuccess = couponFeignService.saveSkuOtherInfo(skuOtherInfoTO);
            if (!isSuccess) {
                log.error("远程调用服务失败，保存sku优惠、满减等信息");
            }
        }
    }

}