package com.laughingather.gulimall.coupon.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laughingather.gulimall.coupon.dao.SpuBoundsDao;
import com.laughingather.gulimall.coupon.entity.SpuBoundsEntity;
import com.laughingather.gulimall.coupon.entity.to.SpuBoundTO;
import com.laughingather.gulimall.coupon.service.SpuBoundsService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service("spuBoundsService")
public class SpuBoundsServiceImpl extends ServiceImpl<SpuBoundsDao, SpuBoundsEntity> implements SpuBoundsService {

    @Resource
    private SpuBoundsDao spuBoundsDao;

    @Override
    public void saveSpuBounds(SpuBoundTO spuBoundTO) {
        SpuBoundsEntity spuBounds = new SpuBoundsEntity();
        BeanUtils.copyProperties(spuBoundTO, spuBounds);

        spuBoundsDao.insert(spuBounds);
    }
}