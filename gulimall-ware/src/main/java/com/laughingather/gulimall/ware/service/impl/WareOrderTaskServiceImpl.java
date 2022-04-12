package com.laughingather.gulimall.ware.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laughingather.gulimall.ware.dao.WareOrderTaskDao;
import com.laughingather.gulimall.ware.entity.WareOrderTaskEntity;
import com.laughingather.gulimall.ware.service.WareOrderTaskService;
import org.springframework.stereotype.Service;


/**
 * 库存锁定清单
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Service("wareOrderTaskService")
public class WareOrderTaskServiceImpl extends ServiceImpl<WareOrderTaskDao, WareOrderTaskEntity> implements WareOrderTaskService {

    @Override
    public WareOrderTaskEntity getWareOrderTaskByOrderSn(String orderSn) {
        QueryWrapper<WareOrderTaskEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(WareOrderTaskEntity::getOrderSn, orderSn);

        return this.getOne(queryWrapper);
    }
}