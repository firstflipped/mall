package com.laughingather.gulimall.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laughingather.gulimall.admin.entity.DictEntity;
import com.laughingather.gulimall.admin.entity.query.DictQuery;
import com.laughingather.gulimall.common.entity.api.MyPage;

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
}
