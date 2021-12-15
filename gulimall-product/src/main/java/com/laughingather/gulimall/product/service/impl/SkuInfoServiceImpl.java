package com.laughingather.gulimall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.product.dao.SkuInfoDao;
import com.laughingather.gulimall.product.entity.SkuImagesEntity;
import com.laughingather.gulimall.product.entity.SkuInfoEntity;
import com.laughingather.gulimall.product.entity.SpuInfoDescEntity;
import com.laughingather.gulimall.product.entity.query.SkuInfoQuery;
import com.laughingather.gulimall.product.entity.vo.ItemSaleAttrVO;
import com.laughingather.gulimall.product.entity.vo.SkuItemVO;
import com.laughingather.gulimall.product.entity.vo.SpuItemGroupAttrVO;
import com.laughingather.gulimall.product.feign.entity.SecKillSkuRedisTO;
import com.laughingather.gulimall.product.feign.service.SecKillFeignService;
import com.laughingather.gulimall.product.service.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;


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

    @Resource
    private SecKillFeignService secKillFeignService;

    @Resource
    private ThreadPoolExecutor threadPoolExecutor;

    @Override
    public MyPage<SkuInfoEntity> listSkusWithPage(SkuInfoQuery skuInfoQuery) {
        IPage<SkuInfoEntity> page = new Page<>(skuInfoQuery.getPageNumber(), skuInfoQuery.getPageSize());
        IPage<SkuInfoEntity> skuInfoIPage = skuInfoDao.listSkusWithPage(page, skuInfoQuery);

        return MyPage.restPage(skuInfoIPage);
    }

    @Override
    public List<SkuInfoEntity> listSkusBySpuId(Long spuId) {
        return skuInfoDao.selectList(new QueryWrapper<SkuInfoEntity>()
                .lambda().eq(SkuInfoEntity::getSpuId, spuId));
    }

    @Override
    public SkuItemVO getSkuItemById(Long skuId) throws ExecutionException, InterruptedException {
        SkuItemVO skuItemVO = new SkuItemVO();

        // 开启异步编排任务
        CompletableFuture<SkuInfoEntity> skuInfoCompletableFuture = CompletableFuture.supplyAsync(() -> {
            // 查询sku基本信息
            SkuInfoEntity skuInfo = getById(skuId);
            skuItemVO.setInfo(skuInfo);
            return skuInfo;
        }, threadPoolExecutor);

        // 需要依赖skuInfo信息的结果
        CompletableFuture<Void> saleAttrsCompletableFuture = skuInfoCompletableFuture.thenAcceptAsync((skuInfo) -> {
            // 当前spu的销售属性组合
            List<ItemSaleAttrVO> saleAttrs = skuSaleAttrValueService.getSaleAttrsBySpuId(skuInfo.getSpuId());
            skuItemVO.setSaleAttrs(saleAttrs);
        }, threadPoolExecutor);

        CompletableFuture<Void> descCompletableFuture = skuInfoCompletableFuture.thenAcceptAsync(skuInfo -> {
            // 获取spu的描述
            SpuInfoDescEntity spuInfoDesc = spuInfoDescService.getById(skuInfo.getSpuId());
            skuItemVO.setDesc(spuInfoDesc);
        }, threadPoolExecutor);

        CompletableFuture<Void> groupAttrsCompletableFuture = skuInfoCompletableFuture.thenAcceptAsync(skuInfo -> {
            // 获取sku的规格参数信息
            List<SpuItemGroupAttrVO> groupAttrVO = attrGroupService.getAttrGroupWithAttrsBySpuId(skuInfo.getCatalogId(), skuInfo.getSpuId());
            skuItemVO.setGroupAttrs(groupAttrVO);
        }, threadPoolExecutor);


        // 不需要依赖skuInfo信息结果，开启一个新的异步
        CompletableFuture<Void> skuImagesCompletableFuture = CompletableFuture.runAsync(() -> {
            // 查询sku的图片信息
            List<SkuImagesEntity> skuImages = skuImagesService.listImagesBySkuId(skuId);
            skuItemVO.setImages(skuImages);
        }, threadPoolExecutor);

        // 不需要依赖skuInfo信息结果，开启一个新的异步
        CompletableFuture<Void> skuSecKillCompletableFuture = CompletableFuture.runAsync(() -> {
            // 查询sku的秒杀信息
            MyResult<SecKillSkuRedisTO> secKillSkuResult = secKillFeignService.getSecKillSkuInfo(skuId);
            if (secKillSkuResult.isSuccess()) {
                skuItemVO.setSecKill(secKillSkuResult.getData());
            }
        }, threadPoolExecutor);


        // 等待所有线程执行完成   skuInfoCompletableFuture可以省略，因为其他异步执行会依赖他的结果
        CompletableFuture.allOf(skuInfoCompletableFuture, saleAttrsCompletableFuture, descCompletableFuture,
                groupAttrsCompletableFuture, skuImagesCompletableFuture, skuSecKillCompletableFuture).get();


        // 默认有货
        skuItemVO.setHasStock(true);

        return skuItemVO;
    }
}