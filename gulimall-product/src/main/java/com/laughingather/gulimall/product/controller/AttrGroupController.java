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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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
@RequestMapping("/product/attr-group")
@Api(tags = "属性分组模块")
public class AttrGroupController {

    @Resource
    private AttrGroupService attrGroupService;


    @GetMapping("/{catId}/page")
    @ApiOperation(value = "根据分类id分页查询属性分组列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "catId", value = "属性分组分类id", dataTypeClass = Long.class),
            @ApiImplicitParam(name = "attrGroupQuery", value = "属性分组过滤条件", dataTypeClass = AttrGroupQuery.class)
    })
    public MyResult<MyPage<AttrGroupEntity>> listAttrGroupsWithPage(@PathVariable("catId") Long categoryId,
                                                                    @ModelAttribute AttrGroupQuery attrGroupQuery) {
        MyPage<AttrGroupEntity> attrGroupEntityMyPage =
                attrGroupService.listAttrGroupsWithPage(categoryId, attrGroupQuery);

        return MyResult.success(attrGroupEntityMyPage);
    }

    @GetMapping("/{attrGroupId}")
    @ApiOperation(value = "根据属性分组id查询属性分组详情")
    @ApiImplicitParam(name = "attrGroupId", value = "属性分组id", dataTypeClass = Long.class)
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
    @GetMapping("/{catId}/with-attrs")
    @ApiOperation(value = "查询当前分类下的所有分组以及分组下的属性信息")
    @ApiImplicitParam(name = "catId", value = "分类id", dataTypeClass = Long.class)
    public MyResult<List<AttrGroupWithAttrsVO>> getAttrGroupWithAttrsByCategoryId(@PathVariable("catId") Long categoryId) {
        List<AttrGroupWithAttrsVO> attrGroupWithAttrsVOList = attrGroupService.getAttrGroupWithAttrsByCategoryId(categoryId);
        return MyResult.success(attrGroupWithAttrsVOList);
    }

    @PostMapping
    @ApiOperation(value = "保存属性分组信息")
    public MyResult saveAttrGroup(@Validated(value = {AddGroup.class}) @RequestBody AttrGroupEntity attrGroup) {
        attrGroupService.save(attrGroup);
        return MyResult.success();
    }

    @DeleteMapping("/{attrGroupId}")
    @ApiOperation(value = "删除属性分组信息")
    @ApiImplicitParam(name = "attrGroupId", value = "属性分组id", dataTypeClass = Long.class)
    public MyResult deleteAttrGroupById(@PathVariable("attrGroupId") Long attrGroupId) {
        attrGroupService.removeById(attrGroupId);
        return MyResult.success();
    }

    @DeleteMapping
    @ApiOperation(value = "批量删除属性分组信息")
    public MyResult deleteAttrGroupsByIds(@RequestBody Long[] attrGroupIds) {
        attrGroupService.removeByIds(Arrays.asList(attrGroupIds));
        return MyResult.success();
    }

    @PutMapping
    @ApiOperation(value = "更新属性分组信息")
    public MyResult updateAttrGroupById(@Validated(value = {UpdateGroup.class}) @RequestBody AttrGroupEntity attrGroup) {
        attrGroupService.updateById(attrGroup);
        return MyResult.success();
    }

}
