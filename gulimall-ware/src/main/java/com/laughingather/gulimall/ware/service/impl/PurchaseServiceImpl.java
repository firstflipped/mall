package com.laughingather.gulimall.ware.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laughingather.gulimall.ware.dao.PurchaseDao;
import com.laughingather.gulimall.ware.entity.PurchaseEntity;
import com.laughingather.gulimall.ware.service.PurchaseService;
import org.springframework.stereotype.Service;


@Service("purchaseService")
public class PurchaseServiceImpl extends ServiceImpl<PurchaseDao, PurchaseEntity> implements PurchaseService {

}