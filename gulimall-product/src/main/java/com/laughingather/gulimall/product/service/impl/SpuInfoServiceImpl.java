package com.laughingather.gulimall.product.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.common.constant.ProductConstants;
import com.laughingather.gulimall.common.util.JsonUtil;
import com.laughingather.gulimall.product.dao.SpuInfoDao;
import com.laughingather.gulimall.product.entity.*;
import com.laughingather.gulimall.product.entity.dto.SkuEsDTO;
import com.laughingather.gulimall.product.entity.dto.SkuOtherInfoDTO;
import com.laughingather.gulimall.product.entity.dto.SpuBoundDTO;
import com.laughingather.gulimall.product.entity.param.*;
import com.laughingather.gulimall.product.entity.query.SpuInfoQuery;
import com.laughingather.gulimall.product.entity.vo.SpuInfoVO;
import com.laughingather.gulimall.product.feign.entity.SkuHasStockDTO;
import com.laughingather.gulimall.product.feign.service.CouponFeignService;
import com.laughingather.gulimall.product.feign.service.SearchFeignService;
import com.laughingather.gulimall.product.feign.service.WareFeignService;
import com.laughingather.gulimall.product.service.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

/**
 * spu逻辑接口
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Slf4j
@Service("spuInfoService")
public class SpuInfoServiceImpl extends ServiceImpl<SpuInfoDao, SpuInfoEntity> implements SpuInfoService {


    @Resource
    private SpuInfoDao spuInfoDao;

    @Resource
    private SpuInfoDescService spuInfoDescService;
    @Resource
    private SpuImagesService spuImagesService;
    @Resource
    private AttrService attrService;
    @Resource
    private ProductAttrValueService productAttrValueService;
    @Resource
    private SkuInfoService skuInfoService;
    @Resource
    private SkuImagesService skuImagesService;
    @Resource
    private SkuSaleAttrValueService skuSaleAttrValueService;
    @Resource
    private BrandService brandService;
    @Resource
    private CategoryService categoryService;
    @Resource
    private CouponFeignService couponFeignService;
    @Resource
    private WareFeignService wareFeignService;
    @Resource
    private SearchFeignService searchFeignService;

    @Resource
    private ThreadPoolExecutor threadPoolExecutor;

    @Override
    public MyPage<SpuInfoVO> listSpuWithPage(SpuInfoQuery spuInfoQuery) {
        IPage<SpuInfoEntity> page = new Page<>(spuInfoQuery.getPn(), spuInfoQuery.getPs());
        IPage<SpuInfoEntity> spuInfoPage = spuInfoDao.listSpuWithPage(page, spuInfoQuery);

        // spu实体转VO
        List<SpuInfoVO> spuInfoVOList = spuInfoPage.getRecords().stream().map(this::spu2SpuVO).collect(Collectors.toList());

        return MyPage.<SpuInfoVO>builder()
                .pageNumber(spuInfoQuery.getPn())
                .pageSize(spuInfoQuery.getPs())
                .pages(spuInfoPage.getPages())
                .total(spuInfoPage.getTotal())
                .list(spuInfoVOList)
                .build();
    }


    @Override
    public SpuInfoVO getSpuInfoBySkuId(Long skuId) {
        return spuInfoDao.getSpuInfoBySkuId(skuId);
    }


    @Override
    public void upSpu(Long spuId) {

        // 查询当前spuId对应的所有sku信息（包括品牌的名称之类）
        List<SkuInfoEntity> skuInfoList = skuInfoService.listSkusBySpuId(spuId);

        // 查询当前spu对应的所有sku可被检索的规格属性
        List<SkuEsDTO.Attr> attrList = getAttrsBySpuId(spuId);

        // 调用仓储服务，查询每个sku是否有库存
        Map<Long, Boolean> stockMap = getHasStock(skuInfoList);

        // 封装每个sku的信息
        List<SkuEsDTO> skuESModels = generateSkuES(skuInfoList, attrList, stockMap);

        // 发送给es服务进行保存
        // TODO：保存到es的时候如果发生异常应该怎么处理
        saveSpu2ES(spuId, skuESModels);
    }


    /**
     * 保存spu的基本信息
     * <p>
     * 注解@GlobalTransactional用来开启分布式全局事务，保证事务一致性，seata分布式事务框架
     *
     * @param spuParam 前端传入spu实体
     */
    @Override
    public void saveSpuInfo(SpuParam spuParam) {

        log.info(JsonUtil.obj2String(spuParam));

        // 保存spu基本信息
        SpuInfoEntity spuInfo = saveSpuBaseInfo(spuParam);
        Long spuInfoId = spuInfo.getId();

        // 保存spu的描述图片
        saveSpuDescription(spuInfoId, spuParam.getSpuDescription());

        // 保存spu的图片集
        saveSpuImages(spuInfoId, spuParam.getImages());

        // 保存spu的规格参数
        saveSpuAttrs(spuInfoId, spuParam.getBaseAttrs());

        // 保存spu的积分信息
        saveSpuBounds(spuInfoId, spuParam.getBounds());

        // 保存spu对应的所有sku信息
        saveSkus(spuInfo, spuParam.getSkus());
    }


    /**
     * 查询当前spu对应的所有sku可被检索的规格属性
     *
     * @param spuId spuId
     * @return es所需属性集合
     */
    private List<SkuEsDTO.Attr> getAttrsBySpuId(Long spuId) {
        // 查询出所有属性及属性值集合
        List<ProductAttrValueEntity> productAttrValues = productAttrValueService.listAttrValuesBySpuId(spuId);
        List<Long> attrIds = productAttrValues.stream().map(ProductAttrValueEntity::getAttrId).collect(Collectors.toList());

        // 查询出需要快速检索的属性
        List<Long> searchAttrIds = attrService.selectSearchAttrIds(attrIds);
        HashSet<Long> searchAttrIdsSet = new HashSet<>(searchAttrIds);

        // 拼装es存储所需属性集合
        return productAttrValues.stream().filter(item ->
                        searchAttrIdsSet.contains(item.getAttrId()))
                .map(item -> {
                    SkuEsDTO.Attr attr = new SkuEsDTO.Attr();
                    BeanUtils.copyProperties(item, attr);
                    return attr;
                }).collect(Collectors.toList());
    }

    /**
     * 查询每个sku的库存
     *
     * @param skuInfoList sku列表
     * @return 库存信息字典
     */
    private Map<Long, Boolean> getHasStock(List<SkuInfoEntity> skuInfoList) {
        // 获取所有的skuId集合
        List<Long> skuIds = skuInfoList.stream().map(SkuInfoEntity::getSkuId).collect(Collectors.toList());

        // 发送远程调用，查询库存系统是否有库存
        Map<Long, Boolean> stockMap = null;
        try {
            MyResult<List<SkuHasStockDTO>> skusHasStockResult = wareFeignService.getSkusHasStock(skuIds);
            if (skusHasStockResult.isSuccess()) {
                stockMap = skusHasStockResult.getData().stream().collect(Collectors.toMap(SkuHasStockDTO::getSkuId, SkuHasStockDTO::getHasStock));
            }
        } catch (Exception e) {
            log.error("远程调用库存查询服务异常", e);
        }
        return stockMap;
    }

    /**
     * 拼装es保存需要的数据
     *
     * @param skuInfoList sku信息集合
     * @param attrList    属性信息集合
     * @param stockMap    是否有库存
     * @return skuEs集合
     */
    private List<SkuEsDTO> generateSkuES(List<SkuInfoEntity> skuInfoList,
                                         List<SkuEsDTO.Attr> attrList,
                                         Map<Long, Boolean> stockMap) {
        return skuInfoList.stream().map(skuInfo -> {
            SkuEsDTO skuEsDTO = new SkuEsDTO();
            BeanUtils.copyProperties(skuInfo, skuEsDTO);

            skuEsDTO.setSkuPrice(skuInfo.getPrice());
            skuEsDTO.setSkuImg(skuInfo.getSkuDefaultImg());

            // 是否有库存
            skuEsDTO.setHasStock(stockMap == null || stockMap.get(skuInfo.getSkuId()));

            // 热度评分，先设置为0
            skuEsDTO.setHotScore(0L);

            // 查询品牌和分类的名字
            BrandEntity brand = brandService.getById(skuEsDTO.getBrandId());
            skuEsDTO.setBrandName(brand.getBrandName());
            skuEsDTO.setBrandImg(brand.getLogo());
            skuEsDTO.setCategoryName(categoryService.getCategoryNameByCategoryId(skuEsDTO.getCategoryId()));

            // 设置检索属性
            skuEsDTO.setAttrs(attrList);
            return skuEsDTO;
        }).collect(Collectors.toList());
    }

    /**
     * 保存到es
     *
     * @param spuId       spuId
     * @param skuESModels skuEs实体集合
     */
    private void saveSpu2ES(Long spuId, List<SkuEsDTO> skuESModels) {
        MyResult<Void> result = searchFeignService.productStatusUp(skuESModels);
        if (result.isSuccess()) {
            // 远程调用成功，修改当前spu的状态
            spuInfoDao.updateSpuStatus(spuId, ProductConstants.StatusEnum.SPU_UP.getCode());
        } else {
            // TODO: 远程调用失败，保存到es失败

        }
    }


    /**
     * 保存spu基本信息
     *
     * @param spuParam 前端传入spu信息
     * @return spu实体
     */
    private SpuInfoEntity saveSpuBaseInfo(SpuParam spuParam) {
        SpuInfoEntity spuInfo = new SpuInfoEntity();
        BeanUtils.copyProperties(spuParam, spuInfo);
        // 设置创建时间
        spuInfo.setCreateTime(LocalDateTime.now());
        this.save(spuInfo);

        return spuInfo;
    }


    /**
     * 保存spu描述信息
     *
     * @param spuInfoId       spuId
     * @param descriptionList 备注集合
     */
    private void saveSpuDescription(Long spuInfoId, List<String> descriptionList) {
        if (CollectionUtils.isNotEmpty(descriptionList)) {
            // 将集合逗号分隔处理成字符串
            String description = String.join(",", descriptionList);

            SpuInfoDescEntity spuInfoDesc = SpuInfoDescEntity.builder().spuId(spuInfoId).description(description).build();
            spuInfoDescService.save(spuInfoDesc);
        }
    }


    /**
     * 保存spu图片信息
     *
     * @param spuInfoId spuId
     * @param images    spu图片集合
     */
    private void saveSpuImages(Long spuInfoId, List<String> images) {
        if (CollectionUtils.isNotEmpty(images)) {
            List<SpuImagesEntity> spuImages = images.stream().map(image ->
                    SpuImagesEntity.builder().spuId(spuInfoId).imgUrl(image).build()
            ).collect(Collectors.toList());
            spuImagesService.saveBatch(spuImages);
        }
    }


    /**
     * 保存spu属性信息
     *
     * @param spuInfoId spuId
     * @param baseAttrs spu基本属性集合
     */
    private void saveSpuAttrs(Long spuInfoId, List<SpuBaseAttrParam> baseAttrs) {
        if (CollectionUtils.isNotEmpty(baseAttrs)) {
            List<ProductAttrValueEntity> productAttrValues = baseAttrs.stream().map(attr -> {
                // 获取属性名
                AttrEntity byId = attrService.getById(attr.getAttrId());
                return ProductAttrValueEntity.builder()
                        .spuId(spuInfoId)
                        .attrId(attr.getAttrId())
                        .attrName(byId == null ? "" : byId.getAttrName()).attrValue(attr.getAttrValues())
                        .quickShow(attr.getQuickShow()).build();
            }).collect(Collectors.toList());

            productAttrValueService.saveBatch(productAttrValues);
        }
    }

    /**
     * 保存spu积分信息
     *
     * @param spuInfoId spuId
     * @param bound     spu积分信息
     */
    private void saveSpuBounds(Long spuInfoId, SpuBoundParam bound) {
        SpuBoundDTO spuBoundDTO = SpuBoundDTO.builder()
                .spuId(spuInfoId)
                .buyBounds(bound.getBuyBounds())
                .growBounds(bound.getGrowBounds())
                .build();

        // 调用优惠服务保存积分信息
        MyResult<Void> saveSpuBoundsResult = couponFeignService.saveSpuBounds(spuBoundDTO);
        if (!saveSpuBoundsResult.isSuccess()) {
            log.error("远程调用服务失败，保存spu的积分信息");
        }
    }


    /**
     * 保存sku集合
     *
     * @param spuInfo spuId
     * @param skus    sku集合
     */
    private void saveSkus(SpuInfoEntity spuInfo, List<SkuParam> skus) {
        if (CollectionUtils.isNotEmpty(skus)) {
            skus.forEach(sku -> {
                // 保存sku基本信息
                SkuInfoEntity skuInfo = saveSkuInfo(spuInfo, sku);
                Long skuId = skuInfo.getSkuId();

                // 保存sku图片信息
                List<SkuImageParam> images = sku.getImages();
                saveSkuImages(skuId, images);

                // 保存sku销售属性信息
                List<SkuAttrParam> attrs = sku.getAttr();
                saveSkuAttrs(skuId, attrs);

                // 保存sku优惠、满减等信息
                saveSkuOther(skuId, sku);
            });
        }
    }


    /**
     * 保存sku基本信息
     *
     * @param spuInfo spuId
     * @param sku     sku
     * @return sku实体
     */
    private SkuInfoEntity saveSkuInfo(SpuInfoEntity spuInfo, SkuParam sku) {
        // 默认图片
        String defaultImg = "";
        for (SkuImageParam image : sku.getImages()) {
            if (ProductConstants.AttrEnum.DEFAULT_IMAGE.getCode().equals(image.getDefaultImg())) {
                defaultImg = image.getImgUrl();
            }
        }

        SkuInfoEntity skuInfo = new SkuInfoEntity();
        BeanUtils.copyProperties(sku, skuInfo);
        skuInfo.setSpuId(spuInfo.getId());
        skuInfo.setBrandId(spuInfo.getBrandId());
        skuInfo.setCategoryId(spuInfo.getCategoryId());
        skuInfo.setSkuDefaultImg(defaultImg);
        skuInfo.setSaleCount(0L);

        skuInfoService.save(skuInfo);
        return skuInfo;
    }


    /**
     * 保存sku图片信息
     *
     * @param skuId  skuId
     * @param images sku图片集合
     */
    private void saveSkuImages(Long skuId, List<SkuImageParam> images) {
        if (CollectionUtils.isNotEmpty(images)) {
            List<SkuImagesEntity> skuImages = images.stream().map(image ->
                    SkuImagesEntity.builder().skuId(skuId)
                            .imgUrl(image.getImgUrl())
                            .defaultImg(image.getDefaultImg())
                            .build()
            ).filter(skuImage ->
                    StringUtils.isBlank(skuImage.getImgUrl())
            ).collect(Collectors.toList());
            skuImagesService.saveBatch(skuImages);
        }
    }

    /**
     * 保存sku属性信息
     *
     * @param skuId skuId
     * @param attrs sku属性集合
     */
    private void saveSkuAttrs(Long skuId, List<SkuAttrParam> attrs) {
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

    /**
     * 保存营销优惠信息
     *
     * @param skuId skuId
     * @param sku   sku前端传入实体
     */
    private void saveSkuOther(Long skuId, SkuParam sku) {
        SkuOtherInfoDTO skuOtherInfoDTO = new SkuOtherInfoDTO();
        BeanUtils.copyProperties(sku, skuOtherInfoDTO);
        skuOtherInfoDTO.setSkuId(skuId);

        // 过滤没有优惠的sku
        if (skuOtherInfoDTO.getFullCount() > 0 && (skuOtherInfoDTO.getFullPrice().compareTo(BigDecimal.ZERO) > 0)) {
            MyResult<Void> saveSkuOtherInfoResult = couponFeignService.saveSkuOtherInfo(skuOtherInfoDTO);
            if (!saveSkuOtherInfoResult.isSuccess()) {
                log.error("远程调用服务失败，保存sku优惠、满减等信息");
            }
        }
    }


    /**
     * spu实体转VO
     *
     * @param spuInfo spu实体
     * @return spuVO
     */
    private SpuInfoVO spu2SpuVO(SpuInfoEntity spuInfo) {
        SpuInfoVO spuInfoVO = new SpuInfoVO();
        BeanUtils.copyProperties(spuInfo, spuInfoVO);

        CompletableFuture<Void> brandNameCompletableFuture = CompletableFuture.runAsync(() -> {
            // 查询品牌名称
            String brandName = brandService.getBrandNameByBrandId(spuInfo.getBrandId());
            spuInfoVO.setBrandName(brandName);
        }, threadPoolExecutor);

        CompletableFuture<Void> categoryNameCompletableFuture = CompletableFuture.runAsync(() -> {
            // 查询
            String categoryName = categoryService.getCategoryNameByCategoryId(spuInfo.getCategoryId());
            spuInfoVO.setCategoryName(categoryName);
        }, threadPoolExecutor);

        CompletableFuture<Void> imageCompletableFuture = CompletableFuture.runAsync(() -> {
            // 查询
            String image = spuImagesService.getDefaultImage(spuInfo.getId());
            spuInfoVO.setImage(image);
        }, threadPoolExecutor);

        // 等待所有线程执行完成   skuInfoCompletableFuture可以省略，因为其他异步执行会依赖他的结果
        try {
            CompletableFuture.allOf(brandNameCompletableFuture, categoryNameCompletableFuture, imageCompletableFuture).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return spuInfoVO;
    }

}