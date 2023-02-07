package com.flipped.mall.coupon.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.flipped.mall.coupon.entity.HomeSubjectSpuEntity;
import com.flipped.mall.coupon.dao.HomeSubjectSpuDao;
import com.flipped.mall.coupon.service.HomeSubjectSpuService;
import org.springframework.stereotype.Service;

/**
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Service("homeSubjectSpuService")
public class HomeSubjectSpuServiceImpl extends ServiceImpl<HomeSubjectSpuDao, HomeSubjectSpuEntity> implements HomeSubjectSpuService {
}