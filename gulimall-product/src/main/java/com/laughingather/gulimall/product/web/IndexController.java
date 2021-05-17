package com.laughingather.gulimall.product.web;

import com.laughingather.gulimall.product.entity.CategoryEntity;
import com.laughingather.gulimall.product.entity.vo.Category2VO;
import com.laughingather.gulimall.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;


/**
 * 引入模板静态引擎后
 * 静态文件放在resources/static下可以直接访问
 * 页面放在resources/templates下可以直接访问
 *
 * @author WangJie
 */
@Controller
public class IndexController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping({"/", "/index.html"})
    public String indexPage(Model model) {

        // 查询所有一级分类
        List<CategoryEntity> categorys = categoryService.listLevel1Category();
        model.addAttribute("categorys", categorys);
        return "index";
    }


    @GetMapping("/index/catalog.json")
    @ResponseBody
    public Map<String, List<Category2VO>> getCatalogJSON() {
        Map<String, List<Category2VO>> catalogJSON = categoryService.getCatalogJSON();
        return catalogJSON;
    }


    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return "hello";
    }

}
