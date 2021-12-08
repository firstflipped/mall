package com.laughingather.gulimall.search.service.impl;

import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.common.api.ResultCodeEnum;
import com.laughingather.gulimall.common.util.JsonUtil;
import com.laughingather.gulimall.search.config.ElasticSearchConfig;
import com.laughingather.gulimall.search.constant.EsConstant;
import com.laughingather.gulimall.search.entity.SkuESModel;
import com.laughingather.gulimall.search.entity.query.SearchQuery;
import com.laughingather.gulimall.search.entity.vo.SearchVO;
import com.laughingather.gulimall.search.feign.entity.AttrVO;
import com.laughingather.gulimall.search.feign.entity.BrandEntity;
import com.laughingather.gulimall.search.feign.service.ProductFeignService;
import com.laughingather.gulimall.search.service.ProductSearchService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.NestedQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.nested.NestedAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.nested.ParsedNested;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedLongTerms;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedStringTerms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author WangJie
 */
@Slf4j
@Service
public class ProductSearchServiceImpl implements ProductSearchService {

    @Resource
    private RestHighLevelClient restHighLevelClient;
    @Resource
    private ProductFeignService productFeignService;

    @Override
    public SearchVO search(SearchQuery searchQuery) {
        SearchVO searchVO = null;

        // 构建检索请求
        SearchRequest searchRequest = buildSearchRequest(searchQuery);
        try {
            SearchResponse searchResponse = restHighLevelClient.search(searchRequest, ElasticSearchConfig.COMMON_OPTIONS);
            // 构建返回参数
            searchVO = buildSearchResult(searchResponse, searchQuery);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return searchVO;
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
        buildAggregation(searchSourceBuilder);

        String DSL = searchSourceBuilder.toString();
        log.info("构建的DSL语句{}", DSL);

        return new SearchRequest(new String[]{EsConstant.PRODUCT_INDEX}, searchSourceBuilder);
    }

    /**
     * 构建结果数据
     *
     * @param searchResponse
     * @param searchQuery
     * @return
     */
    private SearchVO buildSearchResult(SearchResponse searchResponse, SearchQuery searchQuery) {
        log.info("ES检索返回的结果{}", searchResponse);

        SearchVO searchVO = new SearchVO();

        // 商品列表
        buildSearchVOProducts(searchQuery, searchResponse, searchVO);

        // 分类信息
        buildSearchVOCatalogs(searchResponse, searchVO);

        // 品牌信息
        buildSearchVOBrands(searchResponse, searchVO);

        // 属性信息
        buildSearchVOAttrs(searchResponse, searchVO);

        // 分页信息
        buildSearchVOPageInfo(searchQuery, searchResponse, searchVO);

        // 面包屑信息
        buildSearchVONavs(searchQuery, searchResponse, searchVO);

        log.info("拼装的返回结果{}", searchVO);

        return searchVO;
    }


    private void buildSearchVOProducts(SearchQuery searchQuery, SearchResponse searchResponse, SearchVO searchVO) {
        SearchHit[] hits = searchResponse.getHits().getHits();
        List<SkuESModel> skuESModels = new ArrayList<>();
        if (ArrayUtils.isNotEmpty(hits)) {
            for (SearchHit hit : hits) {
                String skuESModelJSON = hit.getSourceAsString();
                SkuESModel skuESModel = JsonUtil.string2Obj(skuESModelJSON, SkuESModel.class);

                // 高亮内容替换
                if (StringUtils.isNotBlank(searchQuery.getKeyword())) {
                    HighlightField skuTitle = hit.getHighlightFields().get("skuTitle");
                    skuESModel.setSkuTitle(skuTitle.getFragments()[0].string());
                }

                skuESModels.add(skuESModel);
            }
        }
        searchVO.setProducts(skuESModels);
    }

    private void buildSearchVOCatalogs(SearchResponse searchResponse, SearchVO searchVO) {
        List<SearchVO.CatalogVO> catalogVOs = new ArrayList<>();
        ParsedLongTerms catalogAgg = searchResponse.getAggregations().get("catalog_agg");
        List<? extends Terms.Bucket> catalogAggBuckets = catalogAgg.getBuckets();
        if (CollectionUtils.isNotEmpty(catalogAggBuckets)) {
            for (Terms.Bucket catalogAggBucket : catalogAggBuckets) {
                SearchVO.CatalogVO catalogVO = new SearchVO.CatalogVO();
                // 设置分类id
                catalogVO.setCatalogId(Long.parseLong(catalogAggBucket.getKeyAsString()));
                // 设置分类名称
                ParsedStringTerms catalogNameAgg = catalogAggBucket.getAggregations().get("catalog_name_agg");
                List<? extends Terms.Bucket> catalogNameAggBuckets = catalogNameAgg.getBuckets();
                catalogVO.setCatalogName(catalogNameAggBuckets.get(0).getKeyAsString());

                catalogVOs.add(catalogVO);
            }
        }
        searchVO.setCatalogs(catalogVOs);
    }

    private void buildSearchVOBrands(SearchResponse searchResponse, SearchVO searchVO) {
        List<SearchVO.BrandVO> brandVOs = new ArrayList<>();
        ParsedLongTerms brandAgg = searchResponse.getAggregations().get("brand_agg");
        List<? extends Terms.Bucket> brandAggBuckets = brandAgg.getBuckets();
        if (CollectionUtils.isNotEmpty(brandAggBuckets)) {
            for (Terms.Bucket brandAggBucket : brandAggBuckets) {

                SearchVO.BrandVO brandVO = new SearchVO.BrandVO();
                // 设置品牌id
                brandVO.setBrandId(brandAggBucket.getKeyAsNumber().longValue());
                // 设置品牌图片
                ParsedStringTerms brandNameAgg = brandAggBucket.getAggregations().get("brand_name_agg");
                List<? extends Terms.Bucket> brandNameAggBuckets = brandNameAgg.getBuckets();
                brandVO.setBrandName(brandNameAggBuckets.get(0).getKeyAsString());

                ParsedStringTerms brandImgAgg = brandAggBucket.getAggregations().get("brand_img_agg");
                List<? extends Terms.Bucket> brandImgAggBuckets = brandImgAgg.getBuckets();
                brandVO.setBrandImg(brandImgAggBuckets.get(0).getKeyAsString());

                brandVOs.add(brandVO);
            }
        }
        searchVO.setBrands(brandVOs);
    }

    private void buildSearchVOAttrs(SearchResponse searchResponse, SearchVO searchVO) {
        List<SearchVO.AttrVO> attrVOs = new ArrayList<>();
        ParsedNested attrAgg = searchResponse.getAggregations().get("attr_agg");
        ParsedLongTerms attrIdAgg = attrAgg.getAggregations().get("attr_id_agg");
        List<? extends Terms.Bucket> attrIdAggBuckets = attrIdAgg.getBuckets();
        if (CollectionUtils.isNotEmpty(attrIdAggBuckets)) {
            for (Terms.Bucket attrIdAggBucket : attrIdAggBuckets) {
                SearchVO.AttrVO attrVO = new SearchVO.AttrVO();
                // 属性id
                attrVO.setAttrId(attrIdAggBucket.getKeyAsNumber().longValue());
                // 属性名称
                ParsedStringTerms attrNameAgg = attrIdAggBucket.getAggregations().get("attr_name_agg");
                attrVO.setAttrName(attrNameAgg.getBuckets().get(0).getKeyAsString());
                // 属性值
                ParsedStringTerms attrValueAgg = attrIdAggBucket.getAggregations().get("attr_value_agg");
                List<? extends Terms.Bucket> attrValueAggBuckets = attrValueAgg.getBuckets();
                List<String> attrVOValues = attrValueAggBuckets.stream().map(item -> item.getKeyAsString()).collect(Collectors.toList());
                attrVO.setAttrValues(attrVOValues);

                attrVOs.add(attrVO);
            }
        }
        searchVO.setAttrs(attrVOs);
    }

    private void buildSearchVOPageInfo(SearchQuery searchQuery, SearchResponse searchResponse, SearchVO searchVO) {
        // 总条数
        long total = searchResponse.getHits().getTotalHits().value;
        searchVO.setTotal(total);
        // 总页码
        long totalPage = total % EsConstant.PAGE_TOTAL == 0 ? total / EsConstant.PAGE_TOTAL : (total / EsConstant.PAGE_TOTAL) + 1;
        searchVO.setTotalPage(totalPage);
        // 页码数组
        List<Long> pageNavs = new ArrayList<>();
        for (long i = 1; i <= totalPage; i++) {
            pageNavs.add(i);
        }
        searchVO.setPageNavs(pageNavs);
        // 当前页码
        searchVO.setPageNum(searchQuery.getPageNum());
    }

    /**
     * 拼装面包屑信息
     *
     * @param searchQuery
     * @param searchResponse
     * @param searchVO
     */
    private void buildSearchVONavs(SearchQuery searchQuery, SearchResponse searchResponse, SearchVO searchVO) {
        if (CollectionUtils.isNotEmpty(searchQuery.getAttrs())) {
            List<SearchVO.NavVO> navVOList = searchQuery.getAttrs().stream().map(attr -> {
                // 分析每个attr传过来的值
                SearchVO.NavVO navVO = new SearchVO.NavVO();

                String[] split = attr.split(EsConstant.ATTR_SPLIT);
                navVO.setNavValue(split[1]);
                MyResult<AttrVO> attrVOById = productFeignService.getAttrVOById(Long.parseLong(split[0]));
                if (attrVOById.getCode().equals(ResultCodeEnum.SUCCESS.getCode())) {
                    navVO.setNavName(attrVOById.getData().getAttrName());
                } else {
                    navVO.setNavName(split[0]);
                }

                // 取消了面包屑之后，我们要跳转到那个地方，将请求地址的url里面的当前置空
                String replace = replaceQueryString(searchQuery, "attrs", attr);
                navVO.setLink(EsConstant.URL + replace);

                return navVO;
            }).collect(Collectors.toList());

            searchVO.setNavs(navVOList);
        }


        if (CollectionUtils.isNotEmpty(searchQuery.getBrandId())) {
            List<SearchVO.NavVO> navs = searchVO.getNavs();
            SearchVO.NavVO navVO = new SearchVO.NavVO();

            navVO.setNavName("品牌");
            List<Long> brandId = searchQuery.getBrandId();
            MyResult<List<BrandEntity>> myResult = productFeignService.listBrandsByIds(brandId);
            if (ResultCodeEnum.SUCCESS.getCode().equals(myResult.getCode())) {
                List<BrandEntity> brands = myResult.getData();
                StringBuffer buffer = new StringBuffer();
                String replace = "";
                for (BrandEntity brand : brands) {
                    buffer.append(brand.getName()).append(";");
                    replace = replaceQueryString(searchQuery, "brandId", brand.getBrandId().toString());
                }
                navVO.setNavValue(buffer.toString());
                navVO.setLink(EsConstant.URL + replace);
            }

            navs.add(navVO);
        }

    }

    private String replaceQueryString(SearchQuery searchQuery, String key, String value) {
        String encode = "";
        try {
            encode = URLEncoder.encode(value, "UTF-8");
            // 浏览器对空格编码和Java不一样
            encode.replace("+", "%20");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String replace = searchQuery.get_queryUrl().replace("&" + key + "=" + encode, "");
        return replace;
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
        if (StringUtils.isNotBlank(searchQuery.getSkuPrice()) && searchQuery.getSkuPrice().contains(EsConstant.PRICE_SPLIT)) {
            String[] price = searchQuery.getSkuPrice().split(EsConstant.PRICE_SPLIT);
            // 表示小于某个价格
            if (searchQuery.getSkuPrice().startsWith(EsConstant.PRICE_SPLIT)) {
                boolQuery.filter(QueryBuilders.rangeQuery("skuPrice").lte(price[0]));
            }
            // 表示大于某个价格
            else if (searchQuery.getSkuPrice().endsWith(EsConstant.PRICE_SPLIT)) {
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
                String[] split = attr.split(EsConstant.ATTR_SPLIT);
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

    private void buildOrder(SearchQuery searchQuery, SearchSourceBuilder searchSourceBuilder) {
        if (StringUtils.isNotBlank(searchQuery.getSort())) {
            // 排序字段格式   字段名_排序方式
            String[] sort = searchQuery.getSort().split(EsConstant.ATTR_SPLIT);
            SortOrder sortOrder = "ASC".equalsIgnoreCase(sort[1]) ? SortOrder.ASC : SortOrder.DESC;
            searchSourceBuilder.sort(sort[0], sortOrder);
        }
    }

    private void buildPage(SearchQuery searchQuery, SearchSourceBuilder searchSourceBuilder) {
        if (searchQuery.getPageNum() == null) {
            searchQuery.setPageNum(1);
        }
        searchSourceBuilder.from((searchQuery.getPageNum() - 1) * EsConstant.PAGE_TOTAL);
        searchSourceBuilder.size(EsConstant.PAGE_TOTAL);
    }

    private void buildHighlight(SearchQuery searchQuery, SearchSourceBuilder searchSourceBuilder) {
        if (StringUtils.isNotBlank(searchQuery.getKeyword())) {
            HighlightBuilder highlightBuilder = new HighlightBuilder();
            highlightBuilder.field("skuTitle");
            highlightBuilder.preTags(EsConstant.PRE_TAGS);
            highlightBuilder.postTags(EsConstant.POST_TAGS);

            searchSourceBuilder.highlighter(highlightBuilder);
        }
    }

    private void buildAggregation(SearchSourceBuilder searchSourceBuilder) {
        // 品牌聚合
        TermsAggregationBuilder brandAgg = AggregationBuilders.terms("brand_agg").field("brandId").size(50);
        // 品牌聚合的子聚合
        brandAgg.subAggregation(AggregationBuilders.terms("brand_name_agg").field("brandName").size(1));
        brandAgg.subAggregation(AggregationBuilders.terms("brand_img_agg").field("brandImg").size(1));
        searchSourceBuilder.aggregation(brandAgg);

        // 分类聚合
        TermsAggregationBuilder catalogAgg = AggregationBuilders.terms("catalog_agg").field("catalogId").size(50);
        // 分类聚合的子聚合
        catalogAgg.subAggregation(AggregationBuilders.terms("catalog_name_agg").field("catalogName").size(1));
        searchSourceBuilder.aggregation(catalogAgg);

        // 属性聚合
        NestedAggregationBuilder attrAgg = AggregationBuilders.nested("attr_agg", "attrs");
        // 分类聚合的子聚合
        TermsAggregationBuilder attrIdAgg = AggregationBuilders.terms("attr_id_agg").field("attrs.attrId");
        attrIdAgg.subAggregation(AggregationBuilders.terms("attr_name_agg").field("attrs.attrName").size(1));
        attrIdAgg.subAggregation(AggregationBuilders.terms("attr_value_agg").field("attrs.attrValue").size(50));
        attrAgg.subAggregation(attrIdAgg);
        searchSourceBuilder.aggregation(attrAgg);
    }
}
