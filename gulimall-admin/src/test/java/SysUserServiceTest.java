import com.laughingather.gulimall.admin.entity.SysUserEntity;
import com.laughingather.gulimall.admin.service.SysUserService;
import com.laughingather.gulimall.common.util.BCryptPasswordEncoderUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * 用户测试类
 *
 * @author <a href="#">flipped</a>
 * @version v1.0
 * @since 2022-04-21 10:34:06
 */
@SpringBootTest(classes = com.laughingather.gulimall.admin.GulimallAdminApplication.class)
@AutoConfigureMockMvc
public class SysUserServiceTest {

    @Resource
    private SysUserService sysUserService;

    @Test
    public void testSaveUser() {
        SysUserEntity sysUserEntity = new SysUserEntity();
        sysUserEntity.setUserid(1L);
        sysUserEntity.setPassword(BCryptPasswordEncoderUtil.encodingPassword("123456"));
        sysUserEntity.setUpdateTime(LocalDateTime.now());
        sysUserService.updateUserById(sysUserEntity);
    }

}

