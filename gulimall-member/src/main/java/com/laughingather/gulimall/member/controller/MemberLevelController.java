package com.laughingather.gulimall.member.controller;

import com.laughingather.gulimall.common.entity.api.MyPage;
import com.laughingather.gulimall.common.entity.api.MyResult;
import com.laughingather.gulimall.member.entity.MemberLevelEntity;
import com.laughingather.gulimall.member.entity.query.MemberLevelQuery;
import com.laughingather.gulimall.member.service.MemberLevelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;


/**
 * 会员等级路由
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@RestController
@RequestMapping("/member/member-level")
@Tag(name = "会员等级模块")
public class MemberLevelController {

    @Resource
    private MemberLevelService memberLevelService;

    @GetMapping("/page")
    @Operation(summary = "分页查询会员等级列表")
    public MyResult<MyPage<MemberLevelEntity>> listMemberLevelsWithPage(@ModelAttribute MemberLevelQuery memberLevelQuery) {
        MyPage<MemberLevelEntity> memberLevelMyPage = memberLevelService.listMemberLevelsWithPage(memberLevelQuery);
        return MyResult.success(memberLevelMyPage);
    }


    @GetMapping("/{id}")
    @Operation(summary = "查询会员等级详情")
    public MyResult<MemberLevelEntity> getMemberLevelById(@PathVariable("id") Long id) {
        MemberLevelEntity memberLevel = memberLevelService.getById(id);
        return MyResult.success(memberLevel);
    }


    @PostMapping
    @Operation(summary = "保存会员等级信息")
    public MyResult<Void> saveMemberLevel(@RequestBody MemberLevelEntity memberLevelEntity) {
        memberLevelService.save(memberLevelEntity);
        return MyResult.success();
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "删除会员等级信息")
    public MyResult<Void> deleteMemberLevel(@PathVariable("id") Long id) {
        memberLevelService.removeById(id);
        return MyResult.success();
    }


    @DeleteMapping
    @Operation(summary = "批量删除会员等级信息")
    public MyResult<Void> deleteBatchMemberLevel(@RequestBody Long[] ids) {
        memberLevelService.removeByIds(Arrays.asList(ids));
        return MyResult.success();
    }


    @PutMapping
    @Operation(summary = "更新会员等级信息")
    public MyResult<Void> updateMemberLevel(@RequestBody MemberLevelEntity memberLevelEntity) {
        memberLevelService.updateById(memberLevelEntity);
        return MyResult.success();
    }

}
