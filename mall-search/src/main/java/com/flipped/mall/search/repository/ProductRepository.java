package com.flipped.mall.search.repository;

import com.flipped.mall.search.entity.EsSku;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * 商品搜索
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
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

