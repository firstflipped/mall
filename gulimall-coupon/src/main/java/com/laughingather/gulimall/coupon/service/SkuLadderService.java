package com.laughingather.gulimall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laughingather.gulimall.coupon.entity.SkuLadderEntity;
import com.laughingather.gulimall.coupon.entity.dto.SkuOtherInfoDTO;

/**
 * 商品阶梯价格
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
public interface SkuLadderService extends IService<SkuLadderEntity> {

    /**
     * 保存商品阶梯价格信息
     *
     * @param skuOtherInfoDTO 商品阶梯价格信息
     */
    void saveSkuLadder(SkuOtherInfoDTO skuOtherInfoDTO);
}

