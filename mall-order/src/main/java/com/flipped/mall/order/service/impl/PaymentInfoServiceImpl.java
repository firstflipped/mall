package com.flipped.mall.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.flipped.mall.order.dao.PaymentInfoDao;
import com.flipped.mall.order.entity.PaymentInfoEntity;
import com.flipped.mall.order.service.PaymentInfoService;
import org.springframework.stereotype.Service;

/**
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Service("paymentInfoService")
public class PaymentInfoServiceImpl extends ServiceImpl<PaymentInfoDao, PaymentInfoEntity> implements PaymentInfoService {

}