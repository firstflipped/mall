import com.laughingather.gulimall.adminnew.entity.SysUserEntity;
import com.laughingather.gulimall.adminnew.service.SysUserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 用户测试类
 *
 * @author：laughingather
 * @create：2021-11-24 2021/11/24
 */
@SpringBootTest(classes = com.laughingather.gulimall.adminnew.GulimallAdminNewApplication.class)
@AutoConfigureMockMvc
public class SysUserServiceTest {

    @Resource
    private SysUserService sysUserService;
    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    public void testSaveUser() {
        SysUserEntity sysUserEntity = new SysUserEntity();
        sysUserEntity.setUsername("wangjie");
        sysUserEntity.setRealName("王杰");
        sysUserEntity.setPassword(bCryptPasswordEncoder.encode("123456"));
        sysUserEntity.setAvatar("https://www.baidu.com");
        sysUserEntity.setBirthday(LocalDate.of(1998, 8, 20));
        sysUserEntity.setBirthday(LocalDate.of(1998, 8, 20));
        sysUserEntity.setSex(0);
        sysUserEntity.setEmail("18763096838@163.com");
        sysUserEntity.setPhone("18763096838");
        sysUserEntity.setStatus(1);
        sysUserEntity.setDelete(0);
        sysUserEntity.setCreateBy("admin");
        sysUserEntity.setCreateTime(LocalDateTime.now());

        sysUserService.saveUser(sysUserEntity);
    }

}

