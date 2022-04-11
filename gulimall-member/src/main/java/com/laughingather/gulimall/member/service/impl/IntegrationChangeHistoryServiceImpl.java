package com.laughingather.gulimall.member.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laughingather.gulimall.member.dao.IntegrationChangeHistoryDao;
import com.laughingather.gulimall.member.entity.IntegrationChangeHistoryEntity;
import com.laughingather.gulimall.member.service.IntegrationChangeHistoryService;
import org.springframework.stereotype.Service;


/**
 * 积分变化历史记录逻辑实现
 *
 * @author laughingather
 */
@Service("integrationChangeHistoryService")
public class IntegrationChangeHistoryServiceImpl extends ServiceImpl<IntegrationChangeHistoryDao, IntegrationChangeHistoryEntity> implements IntegrationChangeHistoryService {

}