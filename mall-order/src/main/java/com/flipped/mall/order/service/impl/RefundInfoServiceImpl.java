package com.flipped.mall.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.flipped.mall.order.dao.RefundInfoDao;
import com.flipped.mall.order.entity.RefundInfoEntity;
import com.flipped.mall.order.service.RefundInfoService;
import org.springframework.stereotype.Service;

/**
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Service("refundInfoService")
public class RefundInfoServiceImpl extends ServiceImpl<RefundInfoDao, RefundInfoEntity> implements RefundInfoService {

}