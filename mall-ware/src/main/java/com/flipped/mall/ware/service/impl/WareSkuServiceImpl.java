package com.flipped.mall.ware.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.flipped.mall.common.constant.WareConstants;
import com.flipped.mall.common.entity.api.MyPage;
import com.flipped.mall.common.entity.api.MyResult;
import com.flipped.mall.common.exception.NoStockException;
import com.flipped.mall.common.util.JsonUtil;
import com.flipped.mall.ware.dao.WareSkuDao;
import com.flipped.mall.ware.entity.SkuWareHasStock;
import com.flipped.mall.ware.entity.WareOrderTaskDetailEntity;
import com.flipped.mall.ware.entity.WareOrderTaskEntity;
import com.flipped.mall.ware.entity.WareSkuEntity;
import com.flipped.mall.ware.entity.dto.StockDetailDTO;
import com.flipped.mall.ware.entity.dto.StockLockedDTO;
import com.flipped.mall.ware.entity.dto.WareSkuLockDTO;
import com.flipped.mall.ware.entity.query.WareSkuQuery;
import com.flipped.mall.ware.entity.vo.OrderItemVO;
import com.flipped.mall.ware.entity.vo.SkuHasStockVO;
import com.flipped.mall.ware.feign.service.ProductFeignService;
import com.flipped.mall.ware.service.WareOrderTaskDetailService;
import com.flipped.mall.ware.service.WareOrderTaskService;
import com.flipped.mall.ware.service.WareSkuService;
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

        IPage<WareSkuEntity> wareSkuPage = wareSkuDao.selectPage(page, queryWrapper);
        return MyPage.restPage(wareSkuPage);
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
            if (!skuNameResult.getSuccess()) {
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
        if (!skuNameResult.getSuccess()) {
            log.info("当前sku不存在，skuId：{}", wareSku.getSkuId());
        }

        wareSku.setSkuName(skuNameResult.getData());
        wareSkuDao.insert(wareSku);
    }

    @Override
    public void updateWareSkuById(WareSkuEntity wareSku) {
        MyResult<String> skuNameResult = productFeignService.getSkuNameBySkuId(wareSku.getSkuId());
        if (!skuNameResult.getSuccess()) {
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

        return skuIds.stream().map(skuId -> {
            SkuHasStockVO skuHasStockVO = new SkuHasStockVO();
            skuHasStockVO.setSkuId(skuId);
            // 根据skuId查询库存信息
            Long stockCount = wareSkuDao.getSkusHasStock(skuId);
            skuHasStockVO.setHasStock(stockCount == null ? Boolean.FALSE : stockCount > 0);
            return skuHasStockVO;
        }).collect(Collectors.toList());
    }


    @Override
    @Transactional(rollbackFor = {Exception.class, RuntimeException.class})
    public Boolean orderLockStock(WareSkuLockDTO wareSkuLockDTO) {
        // 保存库存工作单详情，便于回溯
        WareOrderTaskEntity wareOrderTask = WareOrderTaskEntity.builder().orderSn(wareSkuLockDTO.getOrderSn()).build();
        wareOrderTaskService.save(wareOrderTask);

        // 拼装实体
        List<SkuWareHasStock> skuWareHasStocks = assembleSkuWareHasStocks(wareSkuLockDTO);

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
                            WareConstants.MQEnum.LOCKED_ROUTING_KEY.getName(), JsonUtil.bean2Json(stockLockedDTO));

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
     * @param wareSkuLockDTO
     * @return
     */
    private List<SkuWareHasStock> assembleSkuWareHasStocks(WareSkuLockDTO wareSkuLockDTO) {
        List<OrderItemVO> locks = wareSkuLockDTO.getLocks();
        return locks.stream().map(lock -> {
            SkuWareHasStock skuWareHasStock = new SkuWareHasStock();
            skuWareHasStock.setSkuId(lock.getSkuId());
            skuWareHasStock.setSkuName(lock.getTitle());
            skuWareHasStock.setCount(lock.getCount());
            // 查询该商品有库存的仓库
            List<Long> wareIds = wareSkuDao.listWareIdHasSkuStock(lock.getSkuId());
            skuWareHasStock.setWareIds(wareIds);
            return skuWareHasStock;
        }).collect(Collectors.toList());
    }
}