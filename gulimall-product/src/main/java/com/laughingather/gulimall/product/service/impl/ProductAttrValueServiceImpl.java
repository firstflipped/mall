package com.laughingather.gulimall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.laughingather.gulimall.product.dao.ProductAttrValueDao;
import com.laughingather.gulimall.product.entity.ProductAttrValueEntity;
import com.laughingather.gulimall.product.service.ProductAttrValueService;

import javax.annotation.Resource;
import java.util.List;

/**
 * spu属性值逻辑实现
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-11 15:12:49
 */
@Service("productAttrValueService")
public class ProductAttrValueServiceImpl extends ServiceImpl<ProductAttrValueDao, ProductAttrValueEntity> implements ProductAttrValueService {

    @Resource
    private ProductAttrValueDao productAttrValueDao;

    @Override
    public List<ProductAttrValueEntity> listAttrValuesBySpuId(Long spuId) {
        return productAttrValueDao.selectList(new QueryWrapper<ProductAttrValueEntity>()
                .lambda().eq(ProductAttrValueEntity::getSpuId, spuId));
    }
}