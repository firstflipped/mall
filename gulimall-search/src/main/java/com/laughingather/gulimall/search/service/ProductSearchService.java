package com.laughingather.gulimall.search.service;

import com.laughingather.gulimall.search.entity.query.SearchQuery;
import com.laughingather.gulimall.search.entity.vo.SearchVO;

/**
 * @author WangJie
 */
public interface ProductSearchService {

    /**
     * 根据条件返回检索结果集
     *
     * @param searchQuery
     * @return
     */
    SearchVO search(SearchQuery searchQuery);
}
