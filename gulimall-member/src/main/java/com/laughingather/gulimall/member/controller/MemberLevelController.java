package com.laughingather.gulimall.member.controller;

import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.member.entity.MemberLevelEntity;
import com.laughingather.gulimall.member.entity.query.MemberLevelQuery;
import com.laughingather.gulimall.member.service.MemberLevelService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;


/**
 * 会员等级
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-12 11:33:47
 */
@RestController
@RequestMapping("/member/memberlevel")
public class MemberLevelController {
    @Resource
    private MemberLevelService memberLevelService;

    @GetMapping("/page")
    public MyResult<MyPage<MemberLevelEntity>> pageMemberLevels(@ModelAttribute MemberLevelQuery memberLevelQuery) {
        MyPage<MemberLevelEntity> memberLevelMyPage = memberLevelService.pageMemberLevels(memberLevelQuery);
        return MyResult.success(memberLevelMyPage);
    }

    @GetMapping("/{id}")
    public MyResult<MemberLevelEntity> getMemberLevelById(@PathVariable("id") Long id) {
        MemberLevelEntity memberLevel = memberLevelService.getById(id);
        return MyResult.success(memberLevel);
    }

    @PostMapping
    public MyResult saveMemberLevel(@RequestBody MemberLevelEntity memberLevelEntity) {
        memberLevelService.save(memberLevelEntity);
        return MyResult.success();
    }

    @DeleteMapping("/{id}")
    public MyResult deleteMemberLevelById(@PathVariable("id") Long id) {
        memberLevelService.removeById(id);
        return MyResult.success();
    }

    @DeleteMapping
    public MyResult deleteBatchMemberLevelByIds(@RequestBody Long[] ids) {
        memberLevelService.removeByIds(Arrays.asList(ids));
        return MyResult.success();
    }

    @PutMapping
    public MyResult updateMemberLevelById(@RequestBody MemberLevelEntity memberLevelEntity) {
        memberLevelService.updateById(memberLevelEntity);
        return MyResult.success();
    }

}
