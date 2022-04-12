package com.laughingather.gulimall.ware.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.laughingather.gulimall.common.api.MyPage;
import com.laughingather.gulimall.common.api.MyResult;
import com.laughingather.gulimall.ware.dao.WareInfoDao;
import com.laughingather.gulimall.ware.entity.WareInfoEntity;
import com.laughingather.gulimall.ware.entity.query.WareInfoQuery;
import com.laughingather.gulimall.ware.entity.vo.FareVO;
import com.laughingather.gulimall.ware.feign.entity.MemberReceiveAddressTO;
import com.laughingather.gulimall.ware.feign.service.MemberFeignService;
import com.laughingather.gulimall.ware.service.WareInfoService;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;


/**
 * 仓储逻辑实现
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-11 19:35:16
 */
@Service("wareInfoService")
public class WareInfoServiceImpl extends ServiceImpl<WareInfoDao, WareInfoEntity> implements WareInfoService {

    @Resource
    private WareInfoDao wareInfoDao;

    @Resource
    private MemberFeignService memberFeignService;

    @Override
    public MyPage<WareInfoEntity> listWaresWithPage(WareInfoQuery wareInfoQuery) {
        IPage<WareInfoEntity> page = new Page<>(wareInfoQuery.getPn(), wareInfoQuery.getPs());
        QueryWrapper<WareInfoEntity> queryWrapper = null;
        if (StringUtils.isNotBlank(wareInfoQuery.getKey())) {
            queryWrapper = new QueryWrapper<>();
            queryWrapper.lambda().eq(WareInfoEntity::getId, wareInfoQuery.getKey())
                    .or().like(WareInfoEntity::getName, wareInfoQuery.getKey())
                    .or().like(WareInfoEntity::getAddress, wareInfoQuery.getKey());
        }

        IPage<WareInfoEntity> wareInfoIPage = wareInfoDao.selectPage(page, queryWrapper);
        return MyPage.restPage(wareInfoIPage);
    }

    @Override
    public FareVO getFare(Long addressId) {
        FareVO fareVO = new FareVO();

        MyResult<MemberReceiveAddressTO> addressInfoResult = memberFeignService.getAddressInfoById(addressId);
        MemberReceiveAddressTO address = addressInfoResult.getData();

        // TODO：模拟运费计算
        if (address == null) {
            fareVO.setAddress(null);
            fareVO.setFare(new BigDecimal("0"));
            return fareVO;
        }

        fareVO.setAddress(address);
        fareVO.setFare(new BigDecimal(String.valueOf(RandomUtils.nextInt(5, 20))));
        return fareVO;
    }
}