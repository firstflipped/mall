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
import com.laughingather.gulimall.ware.feign.entity.MemberReceiveAddress;
import com.laughingather.gulimall.ware.feign.service.MemberFeignService;
import com.laughingather.gulimall.ware.service.WareInfoService;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;


@Service("wareInfoService")
public class WareInfoServiceImpl extends ServiceImpl<WareInfoDao, WareInfoEntity> implements WareInfoService {

    @Resource
    private WareInfoDao wareInfoDao;

    @Autowired
    private MemberFeignService memberFeignService;

    @Override
    public MyPage<WareInfoEntity> pageWareInfoByParams(WareInfoQuery wareInfoQuery) {
        IPage<WareInfoEntity> page = new Page<>(wareInfoQuery.getPageNumber(), wareInfoQuery.getPageSize());
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

        MyResult<MemberReceiveAddress> addressInfoResult = memberFeignService.getAddressInfoById(addressId);
        MemberReceiveAddress address = addressInfoResult.getData();

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