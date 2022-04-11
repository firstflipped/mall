package com.laughingather.gulimall.member.controller;

import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.member.entity.MemberLevelEntity;
import com.laughingather.gulimall.member.entity.query.MemberLevelQuery;
import com.laughingather.gulimall.member.service.MemberLevelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "会员等级模块")
public class MemberLevelController {

    @Resource
    private MemberLevelService memberLevelService;

    @GetMapping("/page")
    @ApiOperation(value = "分页查询会员等级列表")
    public MyResult<MyPage<MemberLevelEntity>> listMemberLevelsWithPage(@ModelAttribute MemberLevelQuery memberLevelQuery) {
        MyPage<MemberLevelEntity> memberLevelMyPage = memberLevelService.listMemberLevelsWithPage(memberLevelQuery);
        return MyResult.success(memberLevelMyPage);
    }


    @GetMapping("/{id}")
    @ApiOperation(value = "查询会员等级详情")
    public MyResult<MemberLevelEntity> getMemberLevelById(@PathVariable("id") Long id) {
        MemberLevelEntity memberLevel = memberLevelService.getById(id);
        return MyResult.success(memberLevel);
    }


    @PostMapping
    @ApiOperation(value = "保存会员等级信息")
    public MyResult<Void> saveMemberLevel(@RequestBody MemberLevelEntity memberLevelEntity) {
        memberLevelService.save(memberLevelEntity);
        return MyResult.success();
    }


    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除会员等级信息")
    public MyResult<Void> deleteMemberLevel(@PathVariable("id") Long id) {
        memberLevelService.removeById(id);
        return MyResult.success();
    }


    @DeleteMapping
    @ApiOperation(value = "批量删除会员等级信息")
    public MyResult<Void> deleteBatchMemberLevel(@RequestBody Long[] ids) {
        memberLevelService.removeByIds(Arrays.asList(ids));
        return MyResult.success();
    }


    @PutMapping
    @ApiOperation(value = "更新会员等级信息")
    public MyResult<Void> updateMemberLevel(@RequestBody MemberLevelEntity memberLevelEntity) {
        memberLevelService.updateById(memberLevelEntity);
        return MyResult.success();
    }

}
