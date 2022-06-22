package com.nowcoder.community.dao;

import com.nowcoder.community.entity.User;
import org.apache.ibatis.annotations.Mapper;

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

}
