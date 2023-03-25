package com.wcw.sys.service;

import com.wcw.sys.model.po.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wcw.sys.model.po.UserDto;

import java.util.Map;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author c2w
 * @since 2023-03-22
 */
public interface UserService extends IService<User> {

    String adminLogin(String loginName, String password);

    Map getAdminInfo(String token);

    void adminLogout();
}
