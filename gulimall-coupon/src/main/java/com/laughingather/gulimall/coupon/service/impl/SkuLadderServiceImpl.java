package com.laughingather.gulimall.coupon.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laughingather.gulimall.coupon.dao.SkuLadderDao;
import com.laughingather.gulimall.coupon.entity.SkuLadderEntity;
import com.laughingather.gulimall.coupon.entity.dto.SkuOtherInfoDTO;
import com.laughingather.gulimall.coupon.service.SkuLadderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 *
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Service("skuLadderService")
public class SkuLadderServiceImpl extends ServiceImpl<SkuLadderDao, SkuLadderEntity> implements SkuLadderService {

    @Resource
    private SkuLadderDao skuLadderDao;

    @Override
    public void saveSkuLadder(SkuOtherInfoDTO skuOtherInfoDTO) {
        SkuLadderEntity skuLadder = SkuLadderEntity.builder().skuId(skuOtherInfoDTO.getSkuId())
                .fullCount(skuOtherInfoDTO.getFullCount()).discount(skuOtherInfoDTO.getDiscount())
                .addOther(skuOtherInfoDTO.getCountStatus()).build();
        // 输入优惠条件才插入数据
        if (skuLadder.getFullCount() > 0) {
            skuLadderDao.insert(skuLadder);
        }
    }
}