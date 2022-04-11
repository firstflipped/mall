package com.laughingather.gulimall.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laughingather.gulimall.product.dao.CommentReplayDao;
import com.laughingather.gulimall.product.entity.CommentReplayEntity;
import com.laughingather.gulimall.product.service.CommentReplayService;
import org.springframework.stereotype.Service;

/**
 * 商品评价回复关系逻辑实现
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-11 15:12:49
 */
@Service("commentReplayService")
public class CommentReplayServiceImpl extends ServiceImpl<CommentReplayDao, CommentReplayEntity> implements CommentReplayService {

}