package com.laughingather.gulimall.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laughingather.gulimall.product.dao.ProductAttrValueDao;
import com.laughingather.gulimall.product.entity.ProductAttrValueEntity;
import com.laughingather.gulimall.product.service.ProductAttrValueService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * spu属性值逻辑实现
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
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