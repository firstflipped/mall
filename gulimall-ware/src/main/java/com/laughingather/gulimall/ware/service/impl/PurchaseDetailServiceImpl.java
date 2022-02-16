package com.laughingather.gulimall.ware.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.ware.dao.PurchaseDetailDao;
import com.laughingather.gulimall.ware.entity.PurchaseDetailEntity;
import com.laughingather.gulimall.ware.entity.query.PurchaseDetailQuery;
import com.laughingather.gulimall.ware.service.PurchaseDetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * 采购单详情逻辑实现
 *
 * @author laughingather
 */
@Service("purchaseDetailService")
public class PurchaseDetailServiceImpl extends ServiceImpl<PurchaseDetailDao, PurchaseDetailEntity> implements PurchaseDetailService {

    @Resource
    private PurchaseDetailDao purchaseDetailDao;

    @Override
    public MyPage<PurchaseDetailEntity> listPurchaseDetailsWithPage(PurchaseDetailQuery purchaseDetailQuery) {
        IPage<PurchaseDetailEntity> page = new Page<>(purchaseDetailQuery.getPn(), purchaseDetailQuery.getPs());
        IPage<PurchaseDetailEntity> purchaseDetailIPage = purchaseDetailDao.pagePurchaseDetailByParams(page, purchaseDetailQuery);
        return MyPage.restPage(purchaseDetailIPage);
    }

    @Override
    public List<PurchaseDetailEntity> listPurchaseDetailsByPurchaseId(Long purchaseId) {
        QueryWrapper<PurchaseDetailEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(PurchaseDetailEntity::getPurchaseId, purchaseId);
        return purchaseDetailDao.selectList(queryWrapper);
    }
}