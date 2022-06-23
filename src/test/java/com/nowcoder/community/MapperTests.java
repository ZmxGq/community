package com.nowcoder.community;

import com.nowcoder.community.entity.DiscussPost;
import com.nowcoder.community.dao.DiscussPostMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author zmx
 * @create 2022-06-12 20:46
 */
@SpringBootTest
public class MapperTests {


    @Autowired
    DiscussPostMapper discussPostMapper;

    /**
     * 测试DiscussPostMapper中SelectPosts是否书写正确
     */
    @Test
    public void testSelectPosts(){

        List<DiscussPost> discussPosts = discussPostMapper.selectDiscussPosts(149, 0, 10);

        for (DiscussPost discussPost : discussPosts) {
            System.out.println(discussPost);
        }

        /**
         * 查询某个用户的帖子数目
         * 查询零，就是查询所有帖子数目
         */
        int rows = discussPostMapper.selectDiscussPostRows(149);
        System.out.println(rows);

        System.out.println(111);
        System.out.println("xixi");
        System.out.println("hellogit5");
        System.out.println("hellogit101");
        System.out.println("hellogit102");
        System.out.println("hellogit103");
        System.out.println("yahoo");
        System.out.println("hellogit0");
        System.out.println("hellogit1");


    }
}
