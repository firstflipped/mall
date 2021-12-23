package com.laughingather.gulimall.member.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.laughingather.gulimall.member.dao.GrowthChangeHistoryDao;
import com.laughingather.gulimall.member.entity.GrowthChangeHistoryEntity;
import com.laughingather.gulimall.member.service.GrowthChangeHistoryService;


/**
 * 成长值变化历史记录逻辑实现
 *
 * @author laughingather
 */
@Service("growthChangeHistoryService")
public class GrowthChangeHistoryServiceImpl extends ServiceImpl<GrowthChangeHistoryDao, GrowthChangeHistoryEntity> implements GrowthChangeHistoryService {


}