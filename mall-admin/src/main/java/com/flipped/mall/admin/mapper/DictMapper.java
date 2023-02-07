package com.flipped.mall.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.flipped.mall.admin.entity.DictEntity;
import com.flipped.mall.admin.entity.vo.DictSelectVO;

import java.util.List;

/**
 * 字典持久层
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2023-02-03 14:11:07
 */
public interface DictMapper extends BaseMapper<DictEntity> {

    /**
     * 查询字典列表（仅供前端下拉选择器使用）
     *
     * @return 字典列表
     */
    List<DictSelectVO> listDictWithSelect();
}