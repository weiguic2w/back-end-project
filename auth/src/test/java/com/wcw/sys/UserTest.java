package com.wcw.sys;


import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.wcw.sys.mapper.UserMapper;
import com.wcw.sys.model.po.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

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
        User user = new User();
        user.setUserId(1L);
        //Map<String, Object> userMap = BeanUtil.beanToMap(user, new HashMap<>(),
        //        CopyOptions.create()
        //                .ignoreError()
        //                .ignoreNullValue()
        //                .setFieldValueEditor((fieldName, fieldValue) -> fieldValue.toString()));    // Long类型转为String

        Map<String, Object> userMap2 = BeanUtil.beanToMap(user, new HashMap<>(),
                CopyOptions.create()
                        .ignoreNullValue()
                        .setFieldValueEditor((fieldName, fieldValue) -> {
                            return fieldValue != null ? fieldValue.toString() : null;
                }));
        System.out.println(userMap2);
    }
}
