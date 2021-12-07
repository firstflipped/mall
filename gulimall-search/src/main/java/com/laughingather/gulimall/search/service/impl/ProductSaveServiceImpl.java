package com.laughingather.gulimall.search.service.impl;

import com.laughingather.gulimall.common.utils.JsonUtil;
import com.laughingather.gulimall.search.config.ElasticSearchConfig;
import com.laughingather.gulimall.search.constant.EsConstant;
import com.laughingather.gulimall.search.entity.SkuESModel;
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
 * @author WangJie
 */
@Service
public class ProductSaveServiceImpl implements ProductSaveService {

    @Resource
    private RestHighLevelClient restHighLevelClient;

    @Override
    public boolean productStatusUp(List<SkuESModel> skuESModels) throws IOException {

        // 保存到es
        // 1、给es中建立索引

        // 2、给es中保存这些数据
        // 设置id的话重复操作也不会造成影响，因为存在id的话就会变成修改操作
        BulkRequest bulkRequest = new BulkRequest();
        for (SkuESModel skuESModel : skuESModels) {
            // 构造保存请求
            IndexRequest indexRequest = new IndexRequest(EsConstant.PRODUCT_INDEX);
            indexRequest.id(skuESModel.getSkuId().toString());
            indexRequest.source(JsonUtil.obj2String(skuESModel), XContentType.JSON);

            bulkRequest.add(indexRequest);
        }

        BulkResponse bulk = restHighLevelClient.bulk(bulkRequest, ElasticSearchConfig.COMMON_OPTIONS);

        // TODO: 若果批量错误可以进行后续处理
        boolean isSuccess = bulk.hasFailures();
        return isSuccess;
    }
}
