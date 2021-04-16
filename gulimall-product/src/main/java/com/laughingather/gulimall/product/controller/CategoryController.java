package com.laughingather.gulimall.product.controller;

import com.laughingather.gulimall.common.api.Result;
import com.laughingather.gulimall.product.entity.vo.CategoryTreeVO;
import com.laughingather.gulimall.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 商品三级分类
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-11 15:12:49
 */
@RestController
@RequestMapping("/product/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 查询分类列表，并组装成树
     */
    @GetMapping("/tree")
    public Result treeCategory() {
        List<CategoryTreeVO> categoryTreeVOs = categoryService.listWithTree();
        return Result.success(categoryTreeVOs);
    }

}
