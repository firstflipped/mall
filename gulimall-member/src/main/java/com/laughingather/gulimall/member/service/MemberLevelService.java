package com.laughingather.gulimall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.member.entity.MemberLevelEntity;
import com.laughingather.gulimall.member.entity.query.MemberLevelQuery;

/**
 * 会员等级逻辑接口
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-12 11:33:47
 */
public interface MemberLevelService extends IService<MemberLevelEntity> {

    /**
     * 获取会员等级列表
     *
     * @param memberLevelQuery
     * @return
     */
    MyPage<MemberLevelEntity> listMemberLevelsWithPage(MemberLevelQuery memberLevelQuery);

}

