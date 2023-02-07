package com.flipped.mall.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.flipped.mall.admin.entity.DictDetailEntity;
import org.apache.ibatis.annotations.Param;

/**
 * 字典明细持久层
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2023-02-03 14:21:41
 */
public interface DictDetailMapper extends BaseMapper<DictDetailEntity> {

    /**
     * 根据字典id删除字典明细
     *
     * @param dictId 字典id
     */
    void deleteByDictId(@Param("dictId") Long dictId);
}