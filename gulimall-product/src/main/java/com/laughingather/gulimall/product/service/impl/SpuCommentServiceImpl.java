package com.laughingather.gulimall.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laughingather.gulimall.product.dao.SpuCommentDao;
import com.laughingather.gulimall.product.entity.SpuCommentEntity;
import com.laughingather.gulimall.product.service.SpuCommentService;
import org.springframework.stereotype.Service;

/**
 * 商品评价逻辑实现
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Service("spuCommentService")
public class SpuCommentServiceImpl extends ServiceImpl<SpuCommentDao, SpuCommentEntity> implements SpuCommentService {

}