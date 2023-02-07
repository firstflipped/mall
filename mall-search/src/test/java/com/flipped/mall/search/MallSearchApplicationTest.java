package com.flipped.mall.search;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.flipped.mall.search.entity.EsSku;
import com.flipped.mall.search.repository.ProductRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;

@Slf4j
@SpringBootTest(classes = MallSearchApplication.class)
class MallSearchApplicationTest {

    @Resource
    private RestHighLevelClient restHighLevelClient;
    @Resource
    private ProductRepository productRepository;

    @Test
    public void initSearch() {
        log.info("{}", restHighLevelClient);
    }


    @Test
    public void search() throws IOException {
        SearchRequest searchRequest = new SearchRequest();
        // 指定索引
        searchRequest.indices("bank");
        // 指定DSL
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchRequest.source(searchSourceBuilder);

        SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        log.info("{}", search);
    }


    @Test
    public void createIndex() throws IOException {
        IndexRequest indexRequest = new IndexRequest("users");
        indexRequest.id("1");
        // indexRequest.source("username", "zhangsan", "age", 18, "gender", "男");
        User user = new User();
        user.setUsername("zhangsan");
        user.setAge(18);
        user.setGender("男");
        String userJSON = new JsonMapper().writeValueAsString(user);
        indexRequest.source(userJSON, XContentType.JSON);

        // 执行操作
        IndexResponse index = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);

        // 提取有用的数据
        log.info("{}", index);

    }


    @Test
    public void esDataSelect() throws IOException {
        Iterable<EsSku> all = productRepository.findAll();
        System.out.println(all);
    }


    @Data
    class User {
        private String username;
        private Integer age;
        private String gender;
    }

}
