package com.wcw.sys.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wcw.course.utl.Assert;
import com.wcw.sys.model.po.User;
import com.wcw.sys.mapper.UserMapper;
import com.wcw.sys.model.po.UserDto;
import com.wcw.sys.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.core.util.UuidUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import sun.security.provider.SHA;

import java.util.UUID;

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

    @Override
    public String adminLogin(String loginName, String password) {
        password = DigestUtils.sha1Hex(password);

        User admin = baseMapper.adminLogin(loginName, password);
        Assert.notEmpty(admin, "账号或密码错误");

        //  sa-token 管理登录用户
        StpUtil.login(admin.getUserId());
        // TODO 继承redis存储用户信息
        final UserDto userDto = BeanUtil.copyProperties(admin, UserDto.class);
        userDto.setPassword(null);

        System.out.println(admin);
        return StpUtil.getTokenValueByLoginId(admin.getUserId());
    }

    @Override
    public UserDto getAdminInfo() {
        final String token = StpUtil.getTokenValue();
    //    TODO 从redis中获取用户数据
        return null;
    }


}
