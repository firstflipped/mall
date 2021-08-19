package com.laughingather.gulimall.product.controller;

import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.common.valid.AddGroup;
import com.laughingather.gulimall.common.valid.UpdateGroup;
import com.laughingather.gulimall.product.entity.AttrGroupEntity;
import com.laughingather.gulimall.product.entity.query.AttrGroupQuery;
import com.laughingather.gulimall.product.entity.vo.AttrGroupVO;
import com.laughingather.gulimall.product.entity.vo.AttrGroupWithAttrsVO;
import com.laughingather.gulimall.product.service.AttrGroupService;
import com.laughingather.gulimall.product.service.AttrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;


/**
 * 属性分组
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-11 15:12:49
 */
@RestController
@RequestMapping("/product/attrgroup")
public class AttrGroupController {

    @Autowired
    private AttrGroupService attrGroupService;
    @Autowired
    private AttrService attrService;

    @GetMapping("/page/{catId}")
    public MyResult<MyPage<AttrGroupEntity>> pageAttrGroupsByParams(@PathVariable("catId") Long catId,
                                                                    @ModelAttribute AttrGroupQuery attrGroupQuery) {
        MyPage<AttrGroupEntity> attrGroupEntityMyPage =
                attrGroupService.pageAttrGroupsByParams(catId, attrGroupQuery);

        return MyResult.success(attrGroupEntityMyPage);
    }

    @GetMapping("/{attrGroupId}")
    public MyResult<AttrGroupVO> getAttrGroupById(@PathVariable("attrGroupId") Long attrGroupId) {
        AttrGroupVO attrGroupVO = attrGroupService.getAttrGroupById(attrGroupId);
        return MyResult.success(attrGroupVO);
    }

    /**
     * 查询当前分类下的所有分组以及分组下的属性信息
     *
     * @param categoryId
     * @return
     */
    @GetMapping("/{categoryId}/withAttrs")
    public MyResult<List<AttrGroupWithAttrsVO>> getAttrGroupWithAttrsByCategoryId(@PathVariable("categoryId") Long categoryId) {
        List<AttrGroupWithAttrsVO> attrGroupWithAttrsVOList = attrGroupService.getAttrGroupWithAttrsByCategoryId(categoryId);
        return MyResult.success(attrGroupWithAttrsVOList);
    }

    @PostMapping
    public MyResult saveAttrGroup(@Validated(value = {AddGroup.class}) @RequestBody AttrGroupEntity attrGroup) {
        attrGroupService.save(attrGroup);
        return MyResult.success();
    }

    @DeleteMapping("/{attrGroupId}")
    public MyResult deleteAttrGroupById(@PathVariable("attrGroupId") Long attrGroupId) {
        attrGroupService.removeById(attrGroupId);
        return MyResult.success();
    }

    @DeleteMapping
    public MyResult deleteAttrGroupsByIds(@RequestBody Long[] attrGroupIds) {
        attrGroupService.removeByIds(Arrays.asList(attrGroupIds));
        return MyResult.success();
    }

    @PutMapping
    public MyResult updateAttrGroupById(@Validated(value = {UpdateGroup.class}) @RequestBody AttrGroupEntity attrGroup) {
        attrGroupService.updateById(attrGroup);
        return MyResult.success();
    }

}
