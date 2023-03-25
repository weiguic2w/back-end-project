package com.wcw.sys.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.wcw.course.utl.Assert;
import com.wcw.sys.model.po.User;
import com.wcw.sys.mapper.UserMapper;
import com.wcw.sys.model.po.UserDto;
import com.wcw.sys.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author c2w
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    StringRedisTemplate redisTemplate;

    final long LOGIN_TIME_MINUTES = 1440L;   // 统一分钟单位

    @Override
    public String adminLogin(String loginName, String password) {
        //  通过账号 和 加密密码查询用户是否为空
        password = DigestUtils.sha1Hex(password);
        User admin = baseMapper.adminLogin(loginName, password);
        Assert.notEmpty(admin, "账号或密码错误");

        //  sa-token 管理登录用户
        StpUtil.login(admin.getUserId());
        // redis< token, map<user> >
        UserDto user = BeanUtil.copyProperties(admin, UserDto.class);
        user.setPassword(null);
        String token = StpUtil.getTokenValue();
        Map<String, Object> userMap = BeanUtil.beanToMap(user, new HashMap<>(),
                CopyOptions.create()
                        .setIgnoreNullValue(true)
                        .setFieldValueEditor((fieldName, fieldValue) -> fieldValue==null ? null :fieldValue.toString()));  // 同一转化为String

        redisTemplate.opsForHash().putAll(token, userMap);
         redisTemplate.expire(token,LOGIN_TIME_MINUTES, TimeUnit.MINUTES);
        return token;
    }

    @Override
    public Map getAdminInfo(String token) {
        //  TODO 测试map是否能获取
        Map<Object, Object> userMap = redisTemplate.opsForHash().entries(token);
        //UserDto userDto = BeanUtil.fillBeanWithMap(userMap, new UserDto(), false);
        return userMap;
    }

    @Override
    public void adminLogout() {
        String token = StpUtil.getTokenValue();
        System.out.println(token);
        redisTemplate.delete(token);
    }


}
