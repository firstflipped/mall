package com.laughingather.gulimall.search.web;

import com.laughingather.gulimall.search.entity.query.SearchQuery;
import com.laughingather.gulimall.search.service.ProductSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author WangJie
 */
@Controller
public class SearchController {

    @Autowired
    private ProductSearchService productSearchService;

    @GetMapping("/list.html")
    public String listPage(SearchQuery searchQuery) {
        productSearchService.search(searchQuery);
        return "list";
    }

}
