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
 * sku销售属性&值逻辑实现
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Service("skuSaleAttrValueService")
public class SkuSaleAttrValueServiceImpl extends ServiceImpl<SkuSaleAttrValueDao, SkuSaleAttrValueEntity> implements SkuSaleAttrValueService {

    @Resource
    private SkuSaleAttrValueDao skuSaleAttrValueDao;

    @Override
    public List<ItemSaleAttrVO> getSaleAttrsBySpuId(Long spuId) {
        return skuSaleAttrValueDao.getSaleAttrsBySpuId(spuId);
    }

    @Override
    public List<String> getSkuSaleAttrValuesAsString(Long skuId) {
        return skuSaleAttrValueDao.getSkuSaleAttrValuesAsString(skuId);
    }
}