package com.laughingather.gulimall.search.repository;

import com.laughingather.gulimall.search.entity.EsSku;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author：laughingather
 * @create：2021-12-17 2021/12/17
 */
public interface ProductRepository extends ElasticsearchRepository<EsSku, Long> {

    /**
     * 查询商品列表
     *
     * @param page
     * @param skuTitle
     * @param categoryId
     * @return
     */
    Page<EsSku> findBySkuTitleOrCategoryId(Pageable page, String skuTitle, Long categoryId);

}

