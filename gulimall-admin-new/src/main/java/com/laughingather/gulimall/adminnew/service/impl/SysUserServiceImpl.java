package com.laughingather.gulimall.adminnew.service.impl;

import cn.hutool.core.lang.Snowflake;
import com.laughingather.gulimall.adminnew.entity.SysUserEntity;
import com.laughingather.gulimall.adminnew.mapper.SysUserMapper;
import com.laughingather.gulimall.adminnew.repository.SysUserRepository;
import com.laughingather.gulimall.adminnew.service.SysUserService;
import com.laughingather.gulimall.common.api.MyPage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    private SysUserRepository sysUserRepository;
    @Resource
    private SysUserMapper sysUserMapper;

    @Override
    public void saveUser(SysUserEntity sysUserEntity) {
        sysUserEntity.setId(snowflake.nextId());
        sysUserRepository.save(sysUserEntity);
    }


    @Override
    public void deleteBatchUserByIds(List<Long> userIds) {
        sysUserMapper.deleteBatchIds(userIds);
    }

    @Override
    public void updateUserById(SysUserEntity sysUserEntity) {
        sysUserEntity.setUpdateTime(LocalDateTime.now());
        sysUserRepository.save(sysUserEntity);
    }

    @Override
    public SysUserEntity getUserById(Long userId) {
        return sysUserRepository.getOne(userId);
    }

    @Override
    public List<SysUserEntity> listUsers() {
        return sysUserRepository.findAll();
    }

    @Override
    public MyPage<SysUserEntity> listUserWithPage(Integer pageNum, Integer pageSize) {
        // 数据库分页是从0开始的
        pageNum -= 1;
        Page<SysUserEntity> usersWithPage = sysUserRepository.findAll(PageRequest.of(pageNum, pageSize));

        // 组装成自己的分页信息
        MyPage<SysUserEntity> usersWithMyPage = MyPage.<SysUserEntity>builder().pages(usersWithPage.getTotalPages()).total(usersWithPage.getTotalElements())
                .pageNumber(pageNum).pageSize(pageSize).list(usersWithPage.getContent()).build();
        return usersWithMyPage;
    }

}

