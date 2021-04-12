package com.laughingather.gulimall.product.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laughingather.common.utils.PageUtils;
import com.laughingather.common.utils.Query;

import com.laughingather.gulimall.product.dao.BrandDao;
import com.laughingather.gulimall.product.entity.BrandEntity;
import com.laughingather.gulimall.product.service.BrandService;


@Service("brandService")
public class BrandServiceImpl extends ServiceImpl<BrandDao, BrandEntity> implements BrandService {

}