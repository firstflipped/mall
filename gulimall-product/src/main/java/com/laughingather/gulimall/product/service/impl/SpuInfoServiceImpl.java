package com.laughingather.gulimall.product.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.common.api.ResultCodeEnum;
import com.laughingather.gulimall.common.constant.ProductConstants;
import com.laughingather.gulimall.common.utils.JsonUtil;
import com.laughingather.gulimall.product.dao.SpuInfoDao;
import com.laughingather.gulimall.product.entity.*;
import com.laughingather.gulimall.product.entity.dto.*;
import com.laughingather.gulimall.product.entity.query.SpuInfoQuery;
import com.laughingather.gulimall.product.entity.to.SkuESModel;
import com.laughingather.gulimall.product.entity.to.SkuOtherInfoTO;
import com.laughingather.gulimall.product.entity.to.SpuBoundTO;
import com.laughingather.gulimall.product.entity.vo.SpuInfoVO;
import com.laughingather.gulimall.product.feign.entity.SkuHasStockVO;
import com.laughingather.gulimall.product.feign.service.CouponFeignService;
import com.laughingather.gulimall.product.feign.service.SearchFeignService;
import com.laughingather.gulimall.product.feign.service.WareFeignService;
import com.laughingather.gulimall.product.service.*;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Log4j2
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
    private BrandService brandService;
    @Autowired
    private CategoryService categoryService;
    @Resource
    private CouponFeignService couponFeignService;
    @Resource
    private WareFeignService wareFeignService;
    @Resource
    private SearchFeignService searchFeignService;

    @Override
    public MyPage<SpuInfoEntity> pageSpuInfoByParams(SpuInfoQuery spuInfoQuery) {
        IPage<SpuInfoEntity> page = new Page<>(spuInfoQuery.getPageNumber(), spuInfoQuery.getPageSize());
        IPage<SpuInfoEntity> spuInfoEntityIPage = spuInfoDao.pageSpuInfoByParams(page, spuInfoQuery);
        return MyPage.restPage(spuInfoEntityIPage);
    }

    /**
     * 商品上架
     * TODO：保存到es的时候如果发生异常应该怎么处理
     *
     * @param spuId
     */
    @Override
    public void upSpu(Long spuId) {

        // 查询当前spuId对应的所有sku信息（包括品牌的名称之类）
        List<SkuInfoEntity> skuInfoList = skuInfoService.listSkusBySpuId(spuId);

        // 查询当前spu对应的所有sku可被检索的规格属性
        List<SkuESModel.Attr> attrList = getAttrsBySpuId(spuId);

        // 调用仓储服务，查询每个sku的库存
        Map<Long, Boolean> stockMap = getHasStock(skuInfoList);

        // 封装每个sku的信息
        List<SkuESModel> skuESModels = generateSkuES(skuInfoList, attrList, stockMap);

        // 发送给es服务进行保存
        saveSpu2ES(spuId, skuESModels);
    }


    @Override
    public SpuInfoVO getSpuInfoBySkuId(Long skuId) {
        SpuInfoVO spuInfo = spuInfoDao.getSpuInfoBySkuId(skuId);
        return spuInfo;
    }


    /**
     * 保存到es
     *
     * @param spuId
     * @param skuESModels
     */
    private void saveSpu2ES(Long spuId, List<SkuESModel> skuESModels) {
        MyResult result = searchFeignService.productStatusUp(skuESModels);
        if (ResultCodeEnum.SUCCESS.getCode().equals(result.getCode())) {
            // 远程调用成功，修改当前spu的状态
            spuInfoDao.updateSpuStatus(spuId, ProductConstants.StatusEnum.SPU_UP.getCode());
        } else {
            // 远程调用失败
        }
    }

    /**
     * 拼装es保存需要的数据
     *
     * @param skuInfoList
     * @param attrList
     * @param stockMap
     * @return
     */
    private List<SkuESModel> generateSkuES(List<SkuInfoEntity> skuInfoList, List<SkuESModel.Attr> attrList, Map<Long, Boolean> stockMap) {
        Map<Long, Boolean> finalStockMap = stockMap;
        List<SkuESModel> skuESModels = skuInfoList.stream().map(skuInfo -> {
            SkuESModel skuESModel = new SkuESModel();
            BeanUtils.copyProperties(skuInfo, skuESModel);

            skuESModel.setSkuPrice(skuInfo.getPrice());
            skuESModel.setSkuImg(skuInfo.getSkuDefaultImg());

            // 是否有库存
            skuESModel.setHasStock(finalStockMap == null ? true : finalStockMap.get(skuInfo.getSkuId()));

            // 热度评分，先设置为0
            skuESModel.setHotScore(0L);

            // 查询品牌和分类的名字
            BrandEntity brand = brandService.getById(skuESModel.getBrandId());
            skuESModel.setBrandName(brand.getName());
            skuESModel.setBrandImg(brand.getLogo());

            CategoryEntity category = categoryService.getById(skuESModel.getCatalogId());
            skuESModel.setCatalogName(category.getName());
            // 设置检索属性
            skuESModel.setAttrs(attrList);
            return skuESModel;
        }).collect(Collectors.toList());
        return skuESModels;
    }

    /**
     * 查询每个sku的库存
     *
     * @param skuInfoList
     * @return
     */
    private Map<Long, Boolean> getHasStock(List<SkuInfoEntity> skuInfoList) {
        // 获取所有的skuId集合
        List<Long> skuIds = skuInfoList.stream().map(SkuInfoEntity::getSkuId).collect(Collectors.toList());
        // TODO 发送远程调用，查询库存系统是否有库存
        Map<Long, Boolean> stockMap = null;
        try {
            List<SkuHasStockVO> skusHasStock = wareFeignService.getSkusHasStock(skuIds);
            stockMap = skusHasStock.stream().collect(Collectors.toMap(SkuHasStockVO::getSkuId, item -> item.getHasStock()));
        } catch (Exception e) {
            log.error("库存查询服务异常", e);
        }
        return stockMap;
    }

    /**
     * 查询当前spu对应的所有sku可被检索的规格属性
     *
     * @param spuId
     * @return
     */
    private List<SkuESModel.Attr> getAttrsBySpuId(Long spuId) {
        List<ProductAttrValueEntity> productAttrValues = productAttrValueService.listAttrValuesBySpuId(spuId);
        List<Long> attrIds = productAttrValues.stream().map(attr -> attr.getAttrId()).collect(Collectors.toList());
        List<Long> searchAttrIds = attrService.selectSearchAttrIds(attrIds);
        HashSet<Long> searchAttrIdsSet = new HashSet<>(searchAttrIds);
        List<SkuESModel.Attr> attrList = productAttrValues.stream().filter(item ->
                searchAttrIdsSet.contains(item.getAttrId()))
                .map(item -> {
                    SkuESModel.Attr attr = new SkuESModel.Attr();
                    BeanUtils.copyProperties(item, attr);
                    return attr;
                }).collect(Collectors.toList());
        return attrList;
    }


    /**
     * 保存spu的基本信息
     *
     * @param spuSaveDTO
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveSpuInfo(SpuSaveDTO spuSaveDTO) {

        log.info(JsonUtil.obj2String(spuSaveDTO));

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