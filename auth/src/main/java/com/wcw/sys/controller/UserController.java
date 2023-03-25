package com.wcw.sys.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.wcw.course.result.R;
import com.wcw.course.utl.Assert;
import com.wcw.sys.model.po.UserDto;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;
import com.wcw.sys.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author c2w
 */
@Slf4j
@RestController
@RequestMapping("/auth")
@RefreshScope
public class UserController {

    @Autowired
    private UserService  userService;

    @PostMapping("admin/login")
    public R adminLogin(String loginName, String password) {
        Assert.notEmpty(loginName, "账号为空");
        Assert.notEmpty(password,"密码为空");

        String token = userService.adminLogin(loginName, password);

        return R.ok().message("登录成功").data("token", token);
    }

    @GetMapping("admin/info/{token}")
    public R getAdminInfo(@PathVariable("token")String token) {
        // TODO 不用传送token

        Map userMap = userService.getAdminInfo(token);
        return R.ok().data("info",  userMap);
    }

    @GetMapping("admin/logout")
    public R adminLogout() {
        userService.adminLogout();
        return R.ok();
    }
}
