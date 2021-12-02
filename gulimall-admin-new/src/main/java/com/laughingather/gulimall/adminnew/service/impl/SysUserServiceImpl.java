package com.laughingather.gulimall.adminnew.service.impl;

import cn.hutool.core.lang.Snowflake;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laughingather.gulimall.adminnew.entity.SysUserEntity;
import com.laughingather.gulimall.adminnew.mapper.SysUserMapper;
import com.laughingather.gulimall.adminnew.service.SysUserService;
import com.laughingather.gulimall.common.api.MyPage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 用户逻辑实现
 *
 * @author：laughingather
 * @create：2021-11-24 2021/11/24
 */
@Service
public class SysUserServiceImpl implements SysUserService {

    @Resource
    private Snowflake snowflake;
    @Resource
    private SysUserMapper sysUserMapper;

    @Override
    public void saveUser(SysUserEntity sysUserEntity) {
        sysUserEntity.setId(snowflake.nextId());
        sysUserEntity.setCreateTime(LocalDateTime.now());
        sysUserMapper.insert(sysUserEntity);
    }


    @Override
    public void deleteBatchUserByIds(List<Long> userIds) {
        sysUserMapper.deleteBatchIds(userIds);
    }

    @Override
    public void updateUserById(SysUserEntity sysUserEntity) {
        sysUserEntity.setUpdateTime(LocalDateTime.now());
        sysUserMapper.updateById(sysUserEntity);
    }

    @Override
    public SysUserEntity getUserById(Long userId) {
        return sysUserMapper.selectById(userId);
    }

    @Override
    public List<SysUserEntity> listUsers() {
        return sysUserMapper.selectList(null);
    }

    @Override
    public MyPage<SysUserEntity> listUserWithPage(Integer pageNum, Integer pageSize) {
        // 数据库分页是从0开始的
        IPage<SysUserEntity> usersWithPage = sysUserMapper.selectPage(new Page<>(pageNum, pageSize), null);

        // 组装成自己的分页信息
        MyPage<SysUserEntity> usersWithMyPage = MyPage.restPage(usersWithPage);
        return usersWithMyPage;
    }

}

