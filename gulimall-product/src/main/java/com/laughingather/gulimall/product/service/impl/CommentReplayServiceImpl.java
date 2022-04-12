package com.laughingather.gulimall.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laughingather.gulimall.product.dao.CommentReplayDao;
import com.laughingather.gulimall.product.entity.CommentReplayEntity;
import com.laughingather.gulimall.product.service.CommentReplayService;
import org.springframework.stereotype.Service;

/**
 * 商品评价回复关系逻辑实现
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Service("commentReplayService")
public class CommentReplayServiceImpl extends ServiceImpl<CommentReplayDao, CommentReplayEntity> implements CommentReplayService {

}