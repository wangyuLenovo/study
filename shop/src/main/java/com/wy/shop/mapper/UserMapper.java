package com.wy.shop.mapper;

import com.wy.shop.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface UserMapper {
    /**
     * 查询全部用户信息
     * @return
     */
    List<User> queryUserInfoByPage();
}
