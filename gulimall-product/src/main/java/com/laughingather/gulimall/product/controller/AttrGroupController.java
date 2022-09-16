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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;


/**
 * 属性分组管理路由
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@RestController
@RequestMapping("/product/attr-group")
@Tag(name = "属性分组管理")
public class AttrGroupController {

    @Resource
    private AttrGroupService attrGroupService;

    @GetMapping("/page")
    @Operation(summary = "分页查询属性分组列表")
    public MyResult<MyPage<AttrGroupVO>> listAttrGroupsWithPage(@ModelAttribute AttrGroupQuery attrGroupQuery) {
        MyPage<AttrGroupVO> attrGroupPage = attrGroupService.listAttrGroupsWithPage(attrGroupQuery);
        return MyResult.success(attrGroupPage);
    }

    @GetMapping("/list")
    @Operation(summary = "查询属性分组列表")
    public MyResult<List<AttrGroupEntity>> listAttrGroups() {
        List<AttrGroupEntity> attrGroups = attrGroupService.list();
        return MyResult.success(attrGroups);
    }

    @GetMapping("/{cid}/page")
    @Operation(summary = "根据分类id分页查询属性分组列表")
    @Parameters({
            @Parameter(name = "cid", description = "属性分组分类id"),
            @Parameter(name = "attrGroupQuery", description = "属性分组过滤条件")
    })
    public MyResult<MyPage<AttrGroupEntity>> listAttrGroupsByCategoryIdWithPage(@PathVariable("cid") Long categoryId,
                                                                                @ModelAttribute AttrGroupQuery attrGroupQuery) {
        MyPage<AttrGroupEntity> attrGroupPage =
                attrGroupService.listAttrGroupsByCategoryIdWithPage(categoryId, attrGroupQuery);

        return MyResult.success(attrGroupPage);
    }

    @GetMapping("/{gid}")
    @Operation(summary = "查询属性分组详情信息")
    @Parameter(name = "ag-id", description = "属性分组id")
    public MyResult<AttrGroupVO> getAttrGroupById(@PathVariable("gid") Long attrGroupId) {
        AttrGroupVO attrGroupVO = attrGroupService.getAttrGroupById(attrGroupId);
        return MyResult.success(attrGroupVO);
    }


    @GetMapping("/{cid}/with-attrs")
    @Operation(summary = "查询当前分类下的所有分组以及分组下的属性信息")
    @Parameter(name = "cid", description = "分类id")
    public MyResult<List<AttrGroupWithAttrsVO>> getAttrGroupWithAttrsByCategoryId(@PathVariable("cid") Long categoryId) {
        List<AttrGroupWithAttrsVO> attrGroupWithAttrsVOList = attrGroupService.getAttrGroupWithAttrsByCategoryId(categoryId);
        return MyResult.success(attrGroupWithAttrsVOList);
    }

    @PostMapping
    @Operation(summary = "保存属性分组信息")
    public MyResult<Void> saveAttrGroup(@Validated(value = {AddGroup.class}) @RequestBody AttrGroupEntity attrGroup) {
        attrGroup.setCreateTime(LocalDateTime.now());
        attrGroupService.save(attrGroup);

        return MyResult.success();
    }

    @DeleteMapping("/{gid}")
    @Operation(summary = "删除属性分组信息")
    @Parameter(name = "ag-id", description = "属性分组id")
    public MyResult<Void> deleteAttrGroupById(@PathVariable("gid") Long attrGroupId) {
        attrGroupService.removeById(attrGroupId);
        return MyResult.success();
    }

    @DeleteMapping
    @Operation(summary = "批量删除属性分组信息")
    public MyResult<Void> deleteAttrGroupsByIds(@RequestBody Long[] attrGroupIds) {
        attrGroupService.removeByIds(Arrays.asList(attrGroupIds));
        return MyResult.success();
    }

    @PutMapping
    @Operation(summary = "更新属性分组信息")
    public MyResult<Void> updateAttrGroupById(@Validated(value = {UpdateGroup.class}) @RequestBody AttrGroupEntity attrGroup) {
        attrGroup.setUpdateTime(LocalDateTime.now());
        attrGroupService.updateById(attrGroup);

        return MyResult.success();
    }

}
