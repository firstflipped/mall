package com.laughingather.gulimall.ware.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.ware.dao.WareSkuDao;
import com.laughingather.gulimall.ware.entity.WareSkuEntity;
import com.laughingather.gulimall.ware.entity.query.WareSkuQuery;
import com.laughingather.gulimall.ware.feign.service.ProductFeignService;
import com.laughingather.gulimall.ware.service.WareSkuService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service("wareSkuService")
public class WareSkuServiceImpl extends ServiceImpl<WareSkuDao, WareSkuEntity> implements WareSkuService {

    @Resource
    private WareSkuDao wareSkuDao;
    @Resource
    private ProductFeignService productFeignService;

    @Override
    public MyPage<WareSkuEntity> pageWareSkuByParams(WareSkuQuery wareSkuQuery) {
        IPage<WareSkuEntity> page = new Page<>(wareSkuQuery.getPageNumber(), wareSkuQuery.getPageSize());
        QueryWrapper<WareSkuEntity> queryWrapper = new QueryWrapper<>();
        if (wareSkuQuery.getWareId() != null) {
            queryWrapper.lambda().eq(WareSkuEntity::getWareId, wareSkuQuery.getWareId());
        }
        if (wareSkuQuery.getSkuId() != null) {
            queryWrapper.lambda().eq(WareSkuEntity::getSkuId, wareSkuQuery.getSkuId());
        }

        IPage<WareSkuEntity> wareSkuIPage = wareSkuDao.selectPage(page, queryWrapper);
        return MyPage.restPage(wareSkuIPage);
    }

    /**
     * 更新库存操作
     *
     * @param skuId
     * @param wareId
     * @param skuNum
     */
    @Override
    public void addStock(Long skuId, Long wareId, Integer skuNum) {
        // 如果没有当前的sku和仓库信息，则表示新增
        Integer countBySkuIdAndWareId = wareSkuDao.getCountBySkuIdAndWareId(skuId, wareId);
        if (countBySkuIdAndWareId > 0) {
            wareSkuDao.addStock(skuId, wareId, skuNum);
        } else {
            // TODO 根据skuId查询下skuName（远程服务调用）
            String skuName = productFeignService.getSkuNameBuSkuId(skuId);
            if (StringUtils.isBlank(skuName)) {
                log.info("当前sku不存在，skuId：{}", skuId);
            }
            WareSkuEntity wareSku = WareSkuEntity.builder().skuId(skuId).skuName(skuName)
                    .wareId(wareId).stock(skuNum).stockLocked(0).build();
            wareSkuDao.insert(wareSku);
        }

    }

    @Override
    public void saveWareSku(WareSkuEntity wareSku) {
        String skuName = productFeignService.getSkuNameBuSkuId(wareSku.getSkuId());
        if (StringUtils.isBlank(skuName)) {
            log.info("当前sku不存在，skuId：{}", wareSku.getSkuId());
        }
        wareSku.setSkuName(skuName);
        wareSkuDao.insert(wareSku);
    }

    @Override
    public void updateWareSkuById(WareSkuEntity wareSku) {
        String skuName = productFeignService.getSkuNameBuSkuId(wareSku.getSkuId());
        if (StringUtils.isBlank(skuName)) {
            log.info("当前sku不存在，skuId：{}", wareSku.getSkuId());
        }
        wareSku.setSkuName(skuName);
        wareSkuDao.updateById(wareSku);
    }
}