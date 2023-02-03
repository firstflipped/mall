package com.laughingather.gulimall.admin.service;

import com.laughingather.gulimall.admin.entity.RoleEntity;
import com.laughingather.gulimall.admin.mapper.UserRoleMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-21 10:34:06
 */
@Slf4j
@SpringBootTest(classes = com.laughingather.gulimall.admin.GulimallAdminApplication.class)
public class UserRoleMapperTest {

    @Resource
    private UserRoleMapper userRoleMapper;

    @Test
    public void testSelectPermissionsVO() {
        List<RoleEntity> roles = userRoleMapper.listRolesByUserid(1463452828315029504L);
        log.info("{}", roles);
    }

}

