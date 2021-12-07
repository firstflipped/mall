package com.laughingather.gulimall.search.web;

import com.laughingather.gulimall.search.entity.query.SearchQuery;
import com.laughingather.gulimall.search.entity.vo.SearchVO;
import com.laughingather.gulimall.search.service.ProductSearchService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author WangJie
 */
@Controller
public class SearchController {

    @Resource
    private ProductSearchService productSearchService;

    @GetMapping("/list.html")
    public String listPage(HttpServletRequest request, @ModelAttribute SearchQuery searchQuery, Model model) {
        searchQuery.set_queryUrl(request.getQueryString());

        // 查询数据
        SearchVO search = productSearchService.search(searchQuery);
        model.addAttribute("result", search);

        return "list";
    }

}
