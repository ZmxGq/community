package com.nowcoder.community.service;

import com.nowcoder.community.dao.UserMapper;
import com.nowcoder.community.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zmx
 * @create 2022-06-13 20:35
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 根据用户id查询用户信息
     * @param id
     * @return
     */
    public User findUserById(int id){
        return userMapper.findUserById(id);
    }



}
