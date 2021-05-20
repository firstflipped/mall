package com.laughingather.gulimall.search.service.impl;

import com.laughingather.gulimall.search.config.ElasticSearchConfig;
import com.laughingather.gulimall.search.constant.ESConstant;
import com.laughingather.gulimall.search.entity.query.SearchQuery;
import com.laughingather.gulimall.search.entity.vo.SearchVO;
import com.laughingather.gulimall.search.service.ProductSearchService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.NestedQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author WangJie
 */
@Slf4j
@Service
public class ProductSearchServiceImpl implements ProductSearchService {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Override
    public SearchVO search(SearchQuery searchQuery) {
        // 动态构建出查询需要的DSL语句
        SearchVO searchVO = null;

        SearchRequest searchRequest = buildSearchRequest(searchQuery);
        try {
            SearchResponse searchResponse = restHighLevelClient.search(searchRequest, ElasticSearchConfig.COMMON_OPTIONS);
            searchVO = buildSearchResult(searchResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }


    /**
     * 构建检索请求
     * 模糊匹配、过滤、排序、分页、高亮、聚合分析
     *
     * @param searchQuery
     * @return
     */
    private SearchRequest buildSearchRequest(SearchQuery searchQuery) {

        // 指定检索条件
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        // 查询条件拼装
        buildQuery(searchQuery, searchSourceBuilder);

        // 排序拼装
        buildOrder(searchQuery, searchSourceBuilder);

        // 分页拼装
        buildPage(searchQuery, searchSourceBuilder);

        // 高亮数据拼装
        buildHighlight(searchQuery, searchSourceBuilder);

        // 聚合分析
        TermsAggregationBuilder brandAgg = AggregationBuilders.terms("brand_agg");
        brandAgg.field("brandId");

        String DSL = searchSourceBuilder.toString();
        log.info("构建的DSL语句{}", DSL);

        return new SearchRequest(new String[]{ESConstant.PRODUCT_INDEX}, searchSourceBuilder);
    }

    private void buildHighlight(SearchQuery searchQuery, SearchSourceBuilder searchSourceBuilder) {
        if (StringUtils.isNotBlank(searchQuery.getKeyword())) {
            HighlightBuilder highlightBuilder = new HighlightBuilder();
            highlightBuilder.field("skuTitle");
            highlightBuilder.preTags(ESConstant.PRE_TAGS);
            highlightBuilder.postTags(ESConstant.POST_TAGS);

            searchSourceBuilder.highlighter(highlightBuilder);
        }
    }

    private void buildPage(SearchQuery searchQuery, SearchSourceBuilder searchSourceBuilder) {
        if (searchQuery.getPageNum() == null) {
            searchQuery.setPageNum(1);
        }
        searchSourceBuilder.from((searchQuery.getPageNum() - 1) * ESConstant.PAGE_TOTAL);
        searchSourceBuilder.size(ESConstant.PAGE_TOTAL);
    }

    private void buildOrder(SearchQuery searchQuery, SearchSourceBuilder searchSourceBuilder) {
        if (StringUtils.isNotBlank(searchQuery.getSort())) {
            // 排序字段格式   字段名_排序方式
            String[] sort = searchQuery.getSort().split("_");
            SortOrder sortOrder = sort[1].equalsIgnoreCase("ASC") ? SortOrder.ASC : SortOrder.DESC;
            searchSourceBuilder.sort(sort[0], sortOrder);
        }
    }

    private void buildQuery(SearchQuery searchQuery, SearchSourceBuilder searchSourceBuilder) {
        // 查询条件
        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        if (StringUtils.isNotBlank(searchQuery.getKeyword())) {
            boolQuery.must(QueryBuilders.matchQuery("skuTitle", searchQuery.getKeyword()));
        }

        // 过滤条件
        if (null != searchQuery.getCatalog3Id()) {
            boolQuery.filter(QueryBuilders.termQuery("catalogId", searchQuery.getCatalog3Id()));
        }
        if (CollectionUtils.isNotEmpty(searchQuery.getBrandId())) {
            boolQuery.filter(QueryBuilders.termsQuery("brandId", searchQuery.getBrandId()));
        }
        if (null != searchQuery.getHasStock()) {
            boolQuery.filter(QueryBuilders.termQuery("hasStock", searchQuery.getHasStock().equals(1) ? true : false));
        }
        // 价格参数必须符合 0-500/-500/0- 的格式
        if (StringUtils.isNotBlank(searchQuery.getSkuPrice()) && searchQuery.getSkuPrice().contains(ESConstant.PRICE_SPLIT)) {
            String[] price = searchQuery.getSkuPrice().split(ESConstant.PRICE_SPLIT);
            // 表示小于某个价格
            if (searchQuery.getSkuPrice().startsWith(ESConstant.PRICE_SPLIT)) {
                boolQuery.filter(QueryBuilders.rangeQuery("skuPrice").lte(price[0]));
            }
            // 表示大于某个价格
            else if (searchQuery.getSkuPrice().endsWith(ESConstant.PRICE_SPLIT)) {
                boolQuery.filter(QueryBuilders.rangeQuery("skuPrice").gte(price[0]));
            }
            // 表示价格区间
            else {
                boolQuery.filter(QueryBuilders.rangeQuery("skuPrice").gte(price[0]).lte(price[1]));
            }
        }
        if (CollectionUtils.isNotEmpty(searchQuery.getAttrs())) {
            for (String attr : searchQuery.getAttrs()) {
                BoolQueryBuilder nestedBoolQuery = QueryBuilders.boolQuery();
                String[] split = attr.split(ESConstant.PRICE_SPLIT);
                String attrId = split[0];
                String[] attrValues = split[1].split(":");
                nestedBoolQuery.must(QueryBuilders.termQuery("attrs.attrId", attrId));
                nestedBoolQuery.must(QueryBuilders.termsQuery("attrs.attrName", attrValues));

                // 每此便利都需要生成一个 nested 查询
                NestedQueryBuilder nestedQuery = QueryBuilders.nestedQuery("attrs", nestedBoolQuery, ScoreMode.None);
                boolQuery.filter(nestedQuery);
            }

        }

        searchSourceBuilder.query(boolQuery);
    }

    /**
     * 构建结果数据
     *
     * @param searchResponse
     * @return
     */
    private SearchVO buildSearchResult(SearchResponse searchResponse) {
        SearchVO searchVO = new SearchVO();
        return searchVO;

    }
}
