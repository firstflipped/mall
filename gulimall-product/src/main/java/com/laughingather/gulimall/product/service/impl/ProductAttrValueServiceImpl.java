package com.laughingather.gulimall.product.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laughingather.common.utils.PageUtils;
import com.laughingather.common.utils.Query;

import com.laughingather.gulimall.product.dao.ProductAttrValueDao;
import com.laughingather.gulimall.product.entity.ProductAttrValueEntity;
import com.laughingather.gulimall.product.service.ProductAttrValueService;


@Service("productAttrValueService")
public class ProductAttrValueServiceImpl extends ServiceImpl<ProductAttrValueDao, ProductAttrValueEntity> implements ProductAttrValueService {

}