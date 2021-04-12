package com.laughingather.gulimall.ware.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laughingather.gulimall.ware.dao.PurchaseDetailDao;
import com.laughingather.gulimall.ware.entity.PurchaseDetailEntity;
import com.laughingather.gulimall.ware.service.PurchaseDetailService;
import org.springframework.stereotype.Service;


@Service("purchaseDetailService")
public class PurchaseDetailServiceImpl extends ServiceImpl<PurchaseDetailDao, PurchaseDetailEntity> implements PurchaseDetailService {

}