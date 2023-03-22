package com.wcw.sys;


import cn.dev33.satoken.stp.StpUtil;
import com.wcw.sys.mapper.UserMapper;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author ChuangWeiwei;
 * @create 2023.03.22  17:01;
 */
@SpringBootTest
public class UserTest {

    @Autowired
    UserMapper userMapper;

    @Test
    public void t1() {
        System.out.println(userMapper.getUserInfo("admin", "7c4a8d09ca3762af61e59520943dc26494f8941b"));
    }

    @Test
    public void t2() {
        //User admin userMapper.adminLogin("admin", "7c4a8d09ca3762af61e59520943dc26494f8941b");
        //System.out.println(admin);

        System.out.println(StpUtil.getTokenName());
    }
}
