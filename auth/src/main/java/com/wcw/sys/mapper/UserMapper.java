package com.wcw.sys.mapper;

import com.wcw.sys.model.po.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wcw.sys.model.po.UserDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author c2w
 */
public interface UserMapper extends BaseMapper<User> {
    User getUserInfo(@Param("loginName") String loginName, @Param("password") String password);

    User adminLogin(@Param("loginName") String loginName, @Param("password") String password);
}
