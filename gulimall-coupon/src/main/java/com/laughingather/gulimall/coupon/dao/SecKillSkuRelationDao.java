package com.laughingather.gulimall.coupon.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.laughingather.gulimall.coupon.entity.SecKillSkuRelationEntity;
import com.laughingather.gulimall.coupon.entity.dto.SecKillSkuRelationDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 秒杀活动商品关联
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Mapper
public interface SecKillSkuRelationDao extends BaseMapper<SecKillSkuRelationEntity> {

    /**
     * 查询关联商品
     *
     * @param promotionSessionId
     * @return
     */
    List<SecKillSkuRelationDTO> getRelationSkusByPromotionSessionId(@Param("promotionSessionId") Long promotionSessionId);

}
