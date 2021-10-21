package com.laughingather.gulimall.ware.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.ware.dao.WareSkuDao;
import com.laughingather.gulimall.ware.entity.SkuWareHasStock;
import com.laughingather.gulimall.ware.entity.WareSkuEntity;
import com.laughingather.gulimall.ware.entity.dto.WareSkuLockDTO;
import com.laughingather.gulimall.ware.entity.query.WareSkuQuery;
import com.laughingather.gulimall.ware.entity.vo.OrderItemVO;
import com.laughingather.gulimall.ware.entity.vo.SkuHasStockVO;
import com.laughingather.gulimall.ware.exception.NoStockException;
import com.laughingather.gulimall.ware.feign.service.ProductFeignService;
import com.laughingather.gulimall.ware.service.WareSkuService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author laughingather
 */
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
     * 更新库存操作，增加库存
     *
     * @param skuId 商品id
     * @param wareId 仓库id
     * @param skuNum 锁定数量
     */
    @Override
    public void addStock(Long skuId, Long wareId, Integer skuNum) {
        Integer countBySkuIdAndWareId = wareSkuDao.getCountBySkuIdAndWareId(skuId, wareId);
        // 如果有库存就更新库存
        if (countBySkuIdAndWareId > 0) {
            wareSkuDao.addStock(skuId, wareId, skuNum);
        }
        // 如果没有当前的sku和仓库信息，则表示新增
        else {
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


    /**
     * 第三方服务调用，查询sku是否有库存
     *
     * @param skuIds
     * @return
     */
    @Override
    public List<SkuHasStockVO> getSkusHasStock(List<Long> skuIds) {
        List<SkuHasStockVO> skuHasStockVOList = skuIds.stream().map(skuId -> {
            SkuHasStockVO skuHasStockVO = new SkuHasStockVO();
            skuHasStockVO.setSkuId(skuId);
            // 根据skuId查询库存信息
            Long stockCount = wareSkuDao.getSkusHasStock(skuId);
            skuHasStockVO.setHasStock(stockCount == null ? Boolean.FALSE : stockCount > 0);
            return skuHasStockVO;
        }).collect(Collectors.toList());

        return skuHasStockVOList;
    }


    @Override
    @Transactional(rollbackFor = NoStockException.class)
    public Boolean orderLockStock(WareSkuLockDTO wareSkuLockDTO) {
        List<OrderItemVO> locks = wareSkuLockDTO.getLocks();
        List<SkuWareHasStock> skuWareHasStocks = locks.stream().map(lock -> {
            SkuWareHasStock skuWareHasStock = new SkuWareHasStock();
            skuWareHasStock.setSkuId(lock.getSkuId());
            skuWareHasStock.setCount(lock.getCount());
            // 查询该商品有库存的仓库
            List<Long> wareIds = wareSkuDao.listWareIdHasSkuStock(lock.getSkuId());
            skuWareHasStock.setWareIds(wareIds);
            return skuWareHasStock;
        }).collect(Collectors.toList());


        for (SkuWareHasStock skuWareHasStock : skuWareHasStocks) {
            Boolean skuStocked = false;
            Long skuId = skuWareHasStock.getSkuId();
            List<Long> wareIds = skuWareHasStock.getWareIds();
            if (CollectionUtils.isEmpty(wareIds)) {
                throw new NoStockException(skuId);
            }

            for (Long wareId : wareIds) {
                // 如果返回1表示成功，返回0表示失败
                Long count = wareSkuDao.lockSkuStock(skuId, wareId, skuWareHasStock.getCount());
                // 当前库存锁定失败，就尝试下一个仓库锁定
                if (count.equals(1L)) {
                    skuStocked = true;
                    break;
                }
            }

            // 如果所有仓库都没有锁住库存，则抛出异常
            if (!skuStocked) {
                throw new NoStockException(skuId);
            }
        }

        return true;
    }
}