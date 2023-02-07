package com.flipped.mall.ware.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.flipped.mall.common.entity.api.MyPage;
import com.flipped.mall.common.entity.api.MyResult;
import com.flipped.mall.ware.dao.WareInfoDao;
import com.flipped.mall.ware.entity.WareInfoEntity;
import com.flipped.mall.ware.entity.query.WareInfoQuery;
import com.flipped.mall.ware.entity.vo.FareVO;
import com.flipped.mall.ware.feign.entity.MemberReceiveAddressDTO;
import com.flipped.mall.ware.feign.service.MemberFeignService;
import com.flipped.mall.ware.service.WareInfoService;
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

        MyResult<MemberReceiveAddressDTO> addressInfoResult = memberFeignService.getAddressInfoById(addressId);
        MemberReceiveAddressDTO address = addressInfoResult.getData();

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