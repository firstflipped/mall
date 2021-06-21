package com.laughingather.gulimall.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laughingather.gulimall.product.dao.SkuSaleAttrValueDao;
import com.laughingather.gulimall.product.entity.SkuSaleAttrValueEntity;
import com.laughingather.gulimall.product.entity.vo.ItemSaleAttrVO;
import com.laughingather.gulimall.product.service.SkuSaleAttrValueService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author WangJie
 */

@Service("skuSaleAttrValueService")
public class SkuSaleAttrValueServiceImpl extends ServiceImpl<SkuSaleAttrValueDao, SkuSaleAttrValueEntity> implements SkuSaleAttrValueService {

    @Resource
    private SkuSaleAttrValueDao skuSaleAttrValueDao;

    @Override
    public List<ItemSaleAttrVO> getSaleAttrsBySpuId(Long spuId) {
        // 先根据spuId查询所有skuId
        List<ItemSaleAttrVO> saleAttrs = skuSaleAttrValueDao.getSaleAttrsBySpuId(spuId);
        return saleAttrs;
    }
}