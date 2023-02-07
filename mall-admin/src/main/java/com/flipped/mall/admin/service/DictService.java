package com.flipped.mall.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.flipped.mall.admin.entity.DictEntity;
import com.flipped.mall.admin.entity.query.DictQuery;
import com.flipped.mall.admin.entity.vo.DictSelectVO;
import com.flipped.mall.common.entity.api.MyPage;

import java.util.List;

/**
 * 字典逻辑借口
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2023-02-03 21:26:51
 */
public interface DictService extends IService<DictEntity> {

    /**
     * 分页查询字典列表
     *
     * @param dictQuery 字典查询条件
     * @return 字典列表分页信息
     */
    MyPage<DictEntity> listDictWithPage(DictQuery dictQuery);

    /**
     * 查询字典列表（仅供前端下拉选择器使用）
     *
     * @return 字典列表
     */
    List<DictSelectVO> listDictWithSelect();

    /**
     * 保存字典信息
     *
     * @param dictEntity 字典信息
     */
    void saveDict(DictEntity dictEntity);

    /**
     * 更新字典信息
     *
     * @param dictEntity 字典信息
     */
    void updateDict(DictEntity dictEntity);

    /**
     * 删除字典信息及下属字典明细信息
     *
     * @param did 字典id
     */
    void deleteDictById(Long did);
}
