package com.laughingather.gulimall.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laughingather.gulimall.order.dao.RefundInfoDao;
import com.laughingather.gulimall.order.entity.RefundInfoEntity;
import com.laughingather.gulimall.order.service.RefundInfoService;
import org.springframework.stereotype.Service;


@Service("refundInfoService")
public class RefundInfoServiceImpl extends ServiceImpl<RefundInfoDao, RefundInfoEntity> implements RefundInfoService {

}