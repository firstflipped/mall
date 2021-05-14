package com.laughingather.gulimall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.ware.entity.WareSkuEntity;
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

    MyPage<WareSkuEntity> pageWareSkuByParams(WareSkuQuery wareSkuQuery);

    void addStock(Long skuId, Long wareId, Integer skuNum);

    void saveWareSku(WareSkuEntity wareSku);

    void updateWareSkuById(WareSkuEntity wareSku);

    List<SkuHasStockVO> getSkusHasStock(List<Long> skuIds);
}

