package com.laughingather.gulimall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laughingather.gulimall.ware.entity.WareOrderTaskDetailEntity;

import java.util.List;

/**
 * 库存工作单
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-12 11:57:23
 */
public interface WareOrderTaskDetailService extends IService<WareOrderTaskDetailEntity> {

    /**
     * 更新库存工作单状态
     *
     * @param detailId
     * @param status
     */
    void updateStatusById(Long detailId, Integer status);


    /**
     * 获取锁定库存清单列表（状态为已锁定的）
     *
     * @param taskId
     * @return
     */
    List<WareOrderTaskDetailEntity> listLockerWareOrderTaskDetailByTaskId(Long taskId);

}

