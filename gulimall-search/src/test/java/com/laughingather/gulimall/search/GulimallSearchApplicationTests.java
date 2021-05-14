package com.laughingather.gulimall.search;

import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@Log4j2
@RunWith(SpringRunner.class)
@SpringBootTest
class GulimallSearchApplicationTests {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Test
    public void initSearch() {
        log.info(restHighLevelClient);
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
        log.info(search);
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
        log.info(index);

    }


    @Data
    class User {
        private String username;
        private Integer age;
        private String gender;
    }

}
