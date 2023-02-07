package com.flipped.mall.admin.service;

import com.flipped.mall.admin.MallAdminApplication;
import com.flipped.mall.admin.entity.RoleEntity;
import com.flipped.mall.admin.mapper.UserRoleMapper;
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
@SpringBootTest(classes = MallAdminApplication.class)
public class UserRoleMapperTest {

    @Resource
    private UserRoleMapper userRoleMapper;

    @Test
    public void testSelectPermissionsVO() {
        List<RoleEntity> roles = userRoleMapper.listRolesByUserid(1463452828315029504L);
        log.info("{}", roles);
    }

}

