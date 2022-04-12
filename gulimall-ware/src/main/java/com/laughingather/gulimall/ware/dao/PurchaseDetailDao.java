package com.laughingather.gulimall.ware.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.laughingather.gulimall.ware.entity.PurchaseDetailEntity;
import com.laughingather.gulimall.ware.entity.query.PurchaseDetailQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Mapper
public interface PurchaseDetailDao extends BaseMapper<PurchaseDetailEntity> {

    IPage<PurchaseDetailEntity> pagePurchaseDetailByParams(IPage<PurchaseDetailEntity> page, @Param("params") PurchaseDetailQuery purchaseDetailQuery);
}
