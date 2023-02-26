package com.flipped.mall.member.controller;

import com.flipped.mall.common.entity.api.MyPage;
import com.flipped.mall.common.entity.api.MyResult;
import com.flipped.mall.member.entity.MemberLevelEntity;
import com.flipped.mall.member.entity.query.MemberLevelQuery;
import com.flipped.mall.member.service.MemberLevelService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;


/**
 * 会员等级模块
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@RestController
@RequestMapping("/member/member-level")
public class MemberLevelController {

    @Resource
    private MemberLevelService memberLevelService;

    @GetMapping("/page")
    public MyResult<MyPage<MemberLevelEntity>> listMemberLevelsWithPage(@ModelAttribute MemberLevelQuery memberLevelQuery) {
        MyPage<MemberLevelEntity> memberLevelMyPage = memberLevelService.listMemberLevelsWithPage(memberLevelQuery);
        return MyResult.success(memberLevelMyPage);
    }


    @GetMapping("/{id}")
    public MyResult<MemberLevelEntity> getMemberLevelById(@PathVariable("id") Long id) {
        MemberLevelEntity memberLevel = memberLevelService.getById(id);
        return MyResult.success(memberLevel);
    }


    @PostMapping
    public MyResult<Void> saveMemberLevel(@RequestBody MemberLevelEntity memberLevelEntity) {
        memberLevelService.save(memberLevelEntity);
        return MyResult.success();
    }


    @DeleteMapping("/{id}")
    public MyResult<Void> deleteMemberLevel(@PathVariable("id") Long id) {
        memberLevelService.removeById(id);
        return MyResult.success();
    }


    @DeleteMapping
    public MyResult<Void> deleteBatchMemberLevel(@RequestBody Long[] ids) {
        memberLevelService.removeByIds(Arrays.asList(ids));
        return MyResult.success();
    }


    @PutMapping
    public MyResult<Void> updateMemberLevel(@RequestBody MemberLevelEntity memberLevelEntity) {
        memberLevelService.updateById(memberLevelEntity);
        return MyResult.success();
    }

}
