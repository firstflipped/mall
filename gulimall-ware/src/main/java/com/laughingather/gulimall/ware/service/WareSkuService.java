package com.laughingather.gulimall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.ware.entity.WareSkuEntity;
import com.laughingather.gulimall.ware.entity.dto.WareSkuLockDTO;
import com.laughingather.gulimall.ware.entity.query.WareSkuQuery;
import com.laughingather.gulimall.ware.entity.vo.SkuHasStockVO;

import java.util.List;

/**
 * 商品库存
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-12 11:57:24
 */
public interface WareSkuService extends IService<WareSkuEntity> {

    /**
     * 分页查询库存
     *
     * @param wareSkuQuery
     * @return
     */
    MyPage<WareSkuEntity> pageWareSkuByParams(WareSkuQuery wareSkuQuery);

    /**
     * 添加库存
     *
     * @param skuId
     * @param wareId
     * @param skuNum
     */
    void addStock(Long skuId, Long wareId, Integer skuNum);

    /**
     * 添加sku库存及锁定信息
     *
     * @param wareSku
     */
    void saveWareSku(WareSkuEntity wareSku);

    /**
     * 更新库存锁定信息
     *
     * @param wareSku
     */
    void updateWareSkuById(WareSkuEntity wareSku);

    /**
     * 查询商品是否有货
     *
     * @param skuIds
     * @return
     */
    List<SkuHasStockVO> getSkusHasStock(List<Long> skuIds);

    /**
     * 订单商品库存锁定
     *
     * @param wareSkuLockDTO
     * @return
     */
    Boolean orderLockStock(WareSkuLockDTO wareSkuLockDTO);
}

