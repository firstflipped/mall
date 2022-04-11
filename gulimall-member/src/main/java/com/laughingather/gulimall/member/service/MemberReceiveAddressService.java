package com.laughingather.gulimall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laughingather.gulimall.member.entity.MemberReceiveAddressEntity;

import java.util.List;

/**
 * 会员收货地址逻辑接口
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
public interface MemberReceiveAddressService extends IService<MemberReceiveAddressEntity> {

    /**
     * 获取指定会员的收货地址信息
     *
     * @param memberId 会员id
     * @return
     */
    List<MemberReceiveAddressEntity> listMemberReceiveAddresses(Long memberId);


}

