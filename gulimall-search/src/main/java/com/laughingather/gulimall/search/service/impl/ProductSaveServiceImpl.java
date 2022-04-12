package com.laughingather.gulimall.search.service.impl;

import com.laughingather.gulimall.common.constant.SearchConstants;
import com.laughingather.gulimall.common.util.JsonUtil;
import com.laughingather.gulimall.search.config.MyElasticSearchConfig;
import com.laughingather.gulimall.search.entity.EsSku;
import com.laughingather.gulimall.search.service.ProductSaveService;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Service
public class ProductSaveServiceImpl implements ProductSaveService {

    @Resource
    private RestHighLevelClient restHighLevelClient;

    @Override
    public boolean productUp(List<EsSku> esSkus) throws IOException {

        // 保存到es
        // 1、给es中建立索引

        // 2、给es中保存这些数据
        // 设置id的话重复操作也不会造成影响，因为存在id的话就会变成修改操作
        BulkRequest bulkRequest = new BulkRequest();
        for (EsSku esSku : esSkus) {
            // 构造保存请求
            IndexRequest indexRequest = new IndexRequest(SearchConstants.PRODUCT_INDEX);
            indexRequest.id(esSku.getSkuId().toString());
            indexRequest.source(JsonUtil.obj2String(esSku), XContentType.JSON);

            bulkRequest.add(indexRequest);
        }

        BulkResponse bulk = restHighLevelClient.bulk(bulkRequest, MyElasticSearchConfig.COMMON_OPTIONS);

        // TODO: 如果批量错误可以进行后续处理
        boolean hasFailures = bulk.hasFailures();
        return hasFailures;
    }
}
