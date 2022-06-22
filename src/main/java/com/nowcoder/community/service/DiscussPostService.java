package com.nowcoder.community.service;

import com.nowcoder.community.dao.DiscussPostMapper;
import com.nowcoder.community.entity.DiscussPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zmx
 * @create 2022-06-13 20:30
 */
@Service
public class DiscussPostService {
    /**
     * 注入DiscussPostMapper
     */
    @Autowired
    private DiscussPostMapper discussPostMapper;

    /**
     * 查询目标用户帖子的数目
     * @param userId 用户Id
     * @param offset 起始页码
     * @param limit 每页显示多少条数据
     * @return
     */
    public List<DiscussPost> findDiscussPost(int userId, int offset, int limit){
        return discussPostMapper.selectDiscussPosts(userId,offset,limit);
    }

    /**
     * 查询目标用户帖子的数目
     * @param userId
     * @return
     */
    public int findDiscussPostRows(int userId){
        return discussPostMapper.selectDiscussPostRows(userId);
    }


}
