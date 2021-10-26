package com.laughingather.gulimall.ware.dao;

import com.laughingather.gulimall.ware.entity.WareOrderTaskDetailEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 库存工作单
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-12 11:57:23
 */
@Mapper
public interface WareOrderTaskDetailDao extends BaseMapper<WareOrderTaskDetailEntity> {

    /**
     * 更新库存锁定工作单详情状态
     *
     * @param detailId
     * @param status
     */
    void updateStatusById(@Param("detailId") Long detailId, @Param("status") Integer status);
}
