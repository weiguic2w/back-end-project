package com.wcw.sys.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.wcw.course.result.R;
import com.wcw.course.utl.Assert;
import com.wcw.sys.model.po.UserDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.wcw.sys.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author c2w
 */
@Slf4j
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService  userService;

    public R adminLogin(String loginName, String password) {
        Assert.notEmpty(loginName, "账号为空");
        Assert.notEmpty(password,"密码为空");

        String token = userService.adminLogin(loginName, password);

        return R.ok().message("登录成功").data("satoken", token);
    }

    public R getAdminInfo() {
        UserDto userDto = userService.getAdminInfo();
        return R.ok().data("info", userDto);
    }
}
