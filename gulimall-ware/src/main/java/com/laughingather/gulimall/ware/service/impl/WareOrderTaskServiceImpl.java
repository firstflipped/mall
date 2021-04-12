package com.laughingather.gulimall.ware.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laughingather.gulimall.ware.dao.WareOrderTaskDao;
import com.laughingather.gulimall.ware.entity.WareOrderTaskEntity;
import com.laughingather.gulimall.ware.service.WareOrderTaskService;
import org.springframework.stereotype.Service;


@Service("wareOrderTaskService")
public class WareOrderTaskServiceImpl extends ServiceImpl<WareOrderTaskDao, WareOrderTaskEntity> implements WareOrderTaskService {

}