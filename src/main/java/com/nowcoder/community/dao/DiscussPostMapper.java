package com.nowcoder.community.dao;

import com.nowcoder.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zmx
 * @create 2022-06-12 20:16
 */
@Mapper
public interface DiscussPostMapper {

    /**
     *
     * @param userId 为了以后查询自己的帖子
     * 分页相关
     * @param offset 页码
     * @param limit  每页显示的数据
     * @return
     */
    List<DiscussPost> selectDiscussPosts(int userId, int offset, int limit);

    /**
     * 查询表里一共有多少条数据
     * @param userId
     * @return
     */
    //@Param注解用于给参数取别名
    //如果只有一个参数，并且在<if>里使用，则必须取别名。
    int selectDiscussPostRows(@Param("userId") int userId);





}
