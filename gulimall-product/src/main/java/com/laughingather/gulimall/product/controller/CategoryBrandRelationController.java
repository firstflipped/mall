package com.laughingather.gulimall.product.controller;

import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.common.valid.AddGroup;
import com.laughingather.gulimall.product.entity.CategoryBrandRelationEntity;
import com.laughingather.gulimall.product.entity.dto.CategoryBrandRelationDTO;
import com.laughingather.gulimall.product.entity.vo.CategoryBrandRelationVO;
import com.laughingather.gulimall.product.service.CategoryBrandRelationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 品牌分类关联
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-11 15:12:49
 */
@RestController
@RequestMapping("product/categorybrandrelation")
public class CategoryBrandRelationController {

    @Autowired
    private CategoryBrandRelationService categoryBrandRelationService;

    /**
     * 获取当前品牌关联的所有分类列表
     *
     * @param brandId
     * @return
     */
    @GetMapping("/list/category")
    public MyResult<List<CategoryBrandRelationVO>> listCategoryByBrandId(@RequestParam("brandId") Long brandId) {
        List<CategoryBrandRelationVO> categoryBrandRelationVOList = categoryBrandRelationService.listCategoryByBrandId(brandId);
        return MyResult.success(categoryBrandRelationVOList);
    }


    @PostMapping
    public MyResult saveCategoryBrandRelation(@Validated(value = {AddGroup.class}) @RequestBody
                                                      CategoryBrandRelationDTO categoryBrandRelationDTO) {
        CategoryBrandRelationEntity categoryBrandRelation = new CategoryBrandRelationEntity();
        BeanUtils.copyProperties(categoryBrandRelationDTO, categoryBrandRelation);

        categoryBrandRelationService.saveCategoryBrandRelation(categoryBrandRelation);
        return MyResult.success();
    }

    @DeleteMapping("/{id}")
    public MyResult deleteCategoryBrandRelationById(@PathVariable("id") Long id) {
        categoryBrandRelationService.removeById(id);
        return MyResult.success();
    }

}
