package com.wcw.sys.controller;

import org.springframework.util.Assert;
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

    public void t1() {
    }
}
