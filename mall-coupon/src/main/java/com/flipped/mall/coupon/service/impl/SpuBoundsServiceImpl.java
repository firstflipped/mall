package com.flipped.mall.coupon.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.flipped.mall.coupon.dao.SpuBoundsDao;
import com.flipped.mall.coupon.entity.dto.SpuBoundDTO;
import com.flipped.mall.coupon.entity.SpuBoundsEntity;
import com.flipped.mall.coupon.service.SpuBoundsService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Service("spuBoundsService")
public class SpuBoundsServiceImpl extends ServiceImpl<SpuBoundsDao, SpuBoundsEntity> implements SpuBoundsService {

    @Resource
    private SpuBoundsDao spuBoundsDao;

    @Override
    public void saveSpuBounds(SpuBoundDTO spuBoundDTO) {
        SpuBoundsEntity spuBounds = new SpuBoundsEntity();
        BeanUtils.copyProperties(spuBoundDTO, spuBounds);

        spuBoundsDao.insert(spuBounds);
    }
}