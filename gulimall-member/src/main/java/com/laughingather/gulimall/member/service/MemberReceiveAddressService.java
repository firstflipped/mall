package com.laughingather.gulimall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.laughingather.gulimall.member.entity.MemberReceiveAddressEntity;

import java.util.List;

/**
 * 会员收货地址逻辑接口
 *
 * @author laughingather
 * @email laughingather@gmail.com
 * @date 2021-04-12 11:33:47
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

