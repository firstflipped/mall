package com.laughingather.gulimall.ware.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.common.constant.WareConstants;
import com.laughingather.gulimall.ware.dao.WareSkuDao;
import com.laughingather.gulimall.ware.entity.SkuWareHasStock;
import com.laughingather.gulimall.ware.entity.WareOrderTaskDetailEntity;
import com.laughingather.gulimall.ware.entity.WareOrderTaskEntity;
import com.laughingather.gulimall.ware.entity.WareSkuEntity;
import com.laughingather.gulimall.ware.entity.query.WareSkuQuery;
import com.laughingather.gulimall.ware.entity.to.WareSkuLockTO;
import com.laughingather.gulimall.ware.entity.vo.OrderItemVO;
import com.laughingather.gulimall.ware.entity.vo.SkuHasStockVO;
import com.laughingather.gulimall.ware.exception.NoStockException;
import com.laughingather.gulimall.ware.feign.service.ProductFeignService;
import com.laughingather.gulimall.ware.service.WareOrderTaskDetailService;
import com.laughingather.gulimall.ware.service.WareOrderTaskService;
import com.laughingather.gulimall.ware.service.WareSkuService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Slf4j
@Service("wareSkuService")
public class WareSkuServiceImpl extends ServiceImpl<WareSkuDao, WareSkuEntity> implements WareSkuService {

    @Resource
    private WareSkuDao wareSkuDao;
    @Resource
    private ProductFeignService productFeignService;

    @Resource
    private WareOrderTaskService wareOrderTaskService;
    @Resource
    private WareOrderTaskDetailService wareOrderTaskDetailService;
    @Resource
    private RabbitTemplate rabbitTemplate;

    @Override
    public MyPage<WareSkuEntity> listWareSkusWithPage(WareSkuQuery wareSkuQuery) {
        IPage<WareSkuEntity> page = new Page<>(wareSkuQuery.getPn(), wareSkuQuery.getPs());
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


    @Override
    public void addStock(Long skuId, Long wareId, Integer skuNum) {
        Integer countBySkuIdAndWareId = wareSkuDao.getCountBySkuIdAndWareId(skuId, wareId);
        // 如果有库存就更新库存
        if (countBySkuIdAndWareId > 0) {
            wareSkuDao.addStock(skuId, wareId, skuNum);
        }
        // 如果没有当前的sku和仓库信息，则表示新增
        else {
            MyResult<String> skuNameResult = productFeignService.getSkuNameBySkuId(skuId);
            if (!skuNameResult.isSuccess()) {
                log.info("当前sku不存在，skuId：{}", skuId);
            }
            WareSkuEntity wareSku = WareSkuEntity.builder().skuId(skuId).skuName(skuNameResult.getData())
                    .wareId(wareId).stock(skuNum).stockLocked(0).build();
            wareSkuDao.insert(wareSku);
        }

    }

    @Override
    public void saveWareSku(WareSkuEntity wareSku) {
        MyResult<String> skuNameResult = productFeignService.getSkuNameBySkuId(wareSku.getSkuId());
        if (!skuNameResult.isSuccess()) {
            log.info("当前sku不存在，skuId：{}", wareSku.getSkuId());
        }

        wareSku.setSkuName(skuNameResult.getData());
        wareSkuDao.insert(wareSku);
    }

    @Override
    public void updateWareSkuById(WareSkuEntity wareSku) {
        MyResult<String> skuNameResult = productFeignService.getSkuNameBySkuId(wareSku.getSkuId());
        if (!skuNameResult.isSuccess()) {
            log.info("当前sku不存在，skuId：{}", wareSku.getSkuId());
        }

        wareSku.setSkuName(skuNameResult.getData());
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
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    public Boolean orderLockStock(WareSkuLockTO wareSkuLockTO) {
        // 保存库存工作单详情，便于回溯
        WareOrderTaskEntity wareOrderTask = WareOrderTaskEntity.builder().orderSn(wareSkuLockTO.getOrderSn()).build();
        wareOrderTaskService.save(wareOrderTask);

        // 拼装实体
        List<SkuWareHasStock> skuWareHasStocks = assembleSkuWareHasStocks(wareSkuLockTO);

        // 锁定库存
        lockStock(skuWareHasStocks, wareOrderTask.getId());

        return true;
    }

    @Override
    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    public void unlockStock(Long skuId, Long wareId, Integer skuNum, Long detailId) {
        // 解锁库存
        wareSkuDao.unlockStock(skuId, wareId, skuNum);

        // 更新库存工作单状态为已解锁状态
        wareOrderTaskDetailService.updateStatusById(detailId, WareConstants.StockLockEnum.UNLOCKED.getCode());
    }


    /**
     * 锁定库存
     * 如果每一个商品都锁定成功，将当前商品锁定了的库存详情工作单发送到MQ
     * 如果锁定失败，由于方法是事务执行的所以前面保存的工作单就回滚了，发送到MQ的消息即使要解锁记录，由于数据库中查不到对应的id，也不会执行解锁动作
     *
     * @param skuWareHasStocks 需要锁定库存列表
     * @param wareOrderTaskId  工作单id
     */
    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = {Exception.class, RuntimeException.class})
    public void lockStock(List<SkuWareHasStock> skuWareHasStocks, Long wareOrderTaskId) {
        for (SkuWareHasStock skuWareHasStock : skuWareHasStocks) {
            Boolean skuStocked = false;

            // 获取该商品存在的仓库id
            Long skuId = skuWareHasStock.getSkuId();
            List<Long> wareIds = skuWareHasStock.getWareIds();
            if (CollectionUtils.isEmpty(wareIds)) {
                throw new NoStockException(skuId);
            }


            for (Long wareId : wareIds) {
                // 如果返回1表示成功，返回0表示失败
                Long count = wareSkuDao.lockSkuStock(skuId, wareId, skuWareHasStock.getCount());
                // 如果库存锁定成功则跳出当前循环
                // 库存锁定失败，就尝试下一个仓库锁定
                if (count.equals(1L)) {
                    skuStocked = true;

                    // 添加库存工作单详情
                    WareOrderTaskDetailEntity wareOrderTaskDetail = WareOrderTaskDetailEntity.builder()
                            .skuId(skuId)
                            .skuName(skuWareHasStock.getSkuName())
                            .skuNum(skuWareHasStock.getCount())
                            .taskId(wareOrderTaskId)
                            .wareId(wareId)
                            .lockStatus(WareConstants.StockLockEnum.LOCKED.getCode())
                            .build();
                    wareOrderTaskDetailService.save(wareOrderTaskDetail);

                    // 组装发送到消息队列的实体
                    StockLockedDTO stockLockedDTO = new StockLockedDTO();
                    StockDetailDTO stockDetailDTO = new StockDetailDTO();
                    BeanUtils.copyProperties(wareOrderTaskDetail, stockDetailDTO);
                    stockLockedDTO.setId(wareOrderTaskId);
                    stockLockedDTO.setDetail(stockDetailDTO);

                    // 库存锁定成功就把库存工作单消息添加到rabbitmq
                    rabbitTemplate.convertAndSend(WareConstants.MQEnum.EXCHANGE.getName(),
                            WareConstants.MQEnum.LOCKED_ROUTING_KEY.getName(), stockLockedDTO);

                    break;
                }
            }

            // 如果所有仓库都没有锁住库存，则抛出异常
            if (!skuStocked) {
                throw new NoStockException(skuId);
            }
        }
    }

    /**
     * 拼装商品对应仓库数据
     *
     * @param wareSkuLockTO
     * @return
     */
    private List<SkuWareHasStock> assembleSkuWareHasStocks(WareSkuLockTO wareSkuLockTO) {
        List<OrderItemVO> locks = wareSkuLockTO.getLocks();
        List<SkuWareHasStock> skuWareHasStocks = locks.stream().map(lock -> {
            SkuWareHasStock skuWareHasStock = new SkuWareHasStock();
            skuWareHasStock.setSkuId(lock.getSkuId());
            skuWareHasStock.setSkuName(lock.getTitle());
            skuWareHasStock.setCount(lock.getCount());
            // 查询该商品有库存的仓库
            List<Long> wareIds = wareSkuDao.listWareIdHasSkuStock(lock.getSkuId());
            skuWareHasStock.setWareIds(wareIds);
            return skuWareHasStock;
        }).collect(Collectors.toList());
        return skuWareHasStocks;
    }
}