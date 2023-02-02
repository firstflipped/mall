package com.laughingather.gulimall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laughingather.gulimall.common.entity.api.MyPage;
import com.laughingather.gulimall.member.entity.MemberLevelEntity;
import com.laughingather.gulimall.member.entity.query.MemberLevelQuery;

/**
 * 会员等级逻辑接口
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
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

