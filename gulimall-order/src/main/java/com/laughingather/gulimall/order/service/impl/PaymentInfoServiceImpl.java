package com.laughingather.gulimall.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laughingather.gulimall.order.dao.PaymentInfoDao;
import com.laughingather.gulimall.order.entity.PaymentInfoEntity;
import com.laughingather.gulimall.order.service.PaymentInfoService;
import org.springframework.stereotype.Service;


@Service("paymentInfoService")
public class PaymentInfoServiceImpl extends ServiceImpl<PaymentInfoDao, PaymentInfoEntity> implements PaymentInfoService {

}