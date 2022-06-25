package com.nowcoder.community.dao;

import com.nowcoder.community.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author zmx
 * @create 2022-06-13 20:38
 */
@Mapper
public interface UserMapper {

    /**
     * 根据用户id查询用户信息
     * @param id
     * @return
     */
    User findUserById(int id);

    /**
     * 根据用户的名字查询用户信息
     */
    User findUserByName(String username);

    /**
     * 根据用户邮箱查询用户信息
     */
    User findUserByEmail(String email);

    /**
     * 添加用户
     */
    int insertUser(User user);

    /**
     * 更改用户的激活状态
     */
    int updateStatus(Integer userId, Integer status);

}
