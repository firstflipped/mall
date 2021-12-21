package com.laughingather.gulimall.search;

import com.laughingather.gulimall.search.entity.EsSku;
import com.laughingather.gulimall.search.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;


@SpringBootTest
public class ProductRepositoryTest {


    @Resource
    private ProductRepository productRepository;


    @Test
    public void esDataSelect() {
        Page<EsSku> all = productRepository.findAll(PageRequest.of(1, 10));
        System.out.println(all);
    }

}
