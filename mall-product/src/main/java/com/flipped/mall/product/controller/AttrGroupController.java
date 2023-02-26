package com.flipped.mall.product.controller;

import com.flipped.mall.common.entity.api.MyPage;
import com.flipped.mall.common.entity.api.MyResult;
import com.flipped.mall.common.valid.AddGroup;
import com.flipped.mall.common.valid.UpdateGroup;
import com.flipped.mall.product.entity.AttrGroupEntity;
import com.flipped.mall.product.entity.query.AttrGroupQuery;
import com.flipped.mall.product.entity.vo.AttrGroupVO;
import com.flipped.mall.product.entity.vo.AttrGroupWithAttrsVO;
import com.flipped.mall.product.service.AttrGroupService;
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
 * @since 2022-04-11 19:35:18
 */
@RestController
@RequestMapping("/product/attr-group")
public class AttrGroupController {

    @Resource
    private AttrGroupService attrGroupService;

    @GetMapping("/page")
    public MyResult<MyPage<AttrGroupVO>> listAttrGroupsWithPage(@ModelAttribute AttrGroupQuery attrGroupQuery) {
        MyPage<AttrGroupVO> attrGroupPage = attrGroupService.listAttrGroupsWithPage(attrGroupQuery);
        return MyResult.success(attrGroupPage);
    }

    @GetMapping("/list")
    public MyResult<List<AttrGroupEntity>> listAttrGroups() {
        List<AttrGroupEntity> attrGroups = attrGroupService.list();
        return MyResult.success(attrGroups);
    }

    @GetMapping("/{cid}/page")
    public MyResult<MyPage<AttrGroupEntity>> listAttrGroupsByCategoryIdWithPage(@PathVariable("cid") Long categoryId,
                                                                                @ModelAttribute AttrGroupQuery attrGroupQuery) {
        MyPage<AttrGroupEntity> attrGroupPage =
                attrGroupService.listAttrGroupsByCategoryIdWithPage(categoryId, attrGroupQuery);

        return MyResult.success(attrGroupPage);
    }

    @GetMapping("/{gid}")
    public MyResult<AttrGroupVO> getAttrGroupById(@PathVariable("gid") Long attrGroupId) {
        AttrGroupVO attrGroupVO = attrGroupService.getAttrGroupById(attrGroupId);
        return MyResult.success(attrGroupVO);
    }


    @GetMapping("/{cid}/with-attrs")
    public MyResult<List<AttrGroupWithAttrsVO>> getAttrGroupWithAttrsByCategoryId(@PathVariable("cid") Long categoryId) {
        List<AttrGroupWithAttrsVO> attrGroupWithAttrsVOList = attrGroupService.getAttrGroupWithAttrsByCategoryId(categoryId);
        return MyResult.success(attrGroupWithAttrsVOList);
    }

    @PostMapping
    public MyResult<Void> saveAttrGroup(@Validated(value = {AddGroup.class}) @RequestBody AttrGroupEntity attrGroup) {
        attrGroup.setCreateTime(LocalDateTime.now());
        attrGroupService.save(attrGroup);

        return MyResult.success();
    }

    @DeleteMapping("/{gid}")
    public MyResult<Void> deleteAttrGroupById(@PathVariable("gid") Long attrGroupId) {
        attrGroupService.removeById(attrGroupId);
        return MyResult.success();
    }

    @DeleteMapping
    public MyResult<Void> deleteAttrGroupsByIds(@RequestBody Long[] attrGroupIds) {
        attrGroupService.removeByIds(Arrays.asList(attrGroupIds));
        return MyResult.success();
    }

    @PutMapping
    public MyResult<Void> updateAttrGroupById(@Validated(value = {UpdateGroup.class}) @RequestBody AttrGroupEntity attrGroup) {
        attrGroup.setUpdateTime(LocalDateTime.now());
        attrGroupService.updateById(attrGroup);

        return MyResult.success();
    }

}
