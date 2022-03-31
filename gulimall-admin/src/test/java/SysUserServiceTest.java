import com.laughingather.gulimall.admin.entity.SysUserEntity;
import com.laughingather.gulimall.admin.service.SysUserService;
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
@SpringBootTest(classes = com.laughingather.gulimall.admin.GulimallAdminApplication.class)
@AutoConfigureMockMvc
public class SysUserServiceTest {

    @Resource
    private SysUserService sysUserService;
    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    public void testSaveUser() {
        SysUserEntity sysUserEntity = new SysUserEntity();
        sysUserEntity.setUsername("wangwu");
        sysUserEntity.setRealName("王五");
        sysUserEntity.setPassword(bCryptPasswordEncoder.encode("123456"));
        sysUserEntity.setAvatar("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fup.enterdesk.com%2Fedpic%2F69%2F5f%2Fa7%2F695fa728c162c2cb073d7e0079dfdee5.jpeg&refer=http%3A%2F%2Fup.enterdesk.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1647497570&t=ec87f37033839d93bfc8b10be8031e31");
        sysUserEntity.setBirthday(LocalDate.of(1996, 9, 20));
        sysUserEntity.setGender(0);
        sysUserEntity.setEmail("9999999@qq.com");
        sysUserEntity.setPhone("13377777777");
        sysUserEntity.setStatus(1);
        sysUserEntity.setDelete(0);
        sysUserEntity.setCreateTime(LocalDateTime.now());

        sysUserService.saveUser(sysUserEntity);
    }

}

