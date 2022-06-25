package com.nowcoder.community;

import com.nowcoder.community.entity.DiscussPost;
import com.nowcoder.community.dao.DiscussPostMapper;
import com.nowcoder.community.entity.User;
import com.nowcoder.community.service.UserService;
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
    private DiscussPostMapper discussPostMapper;

    @Autowired
    private UserService userService;

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


    }

    @Test
    public void testUserMapper(){
//        System.out.println(userService.findUserByName(""));
//        System.out.println(userService.findUserByEmail("@sina.com"));
//        User user = new User();
//        user.setUsername("123");
//        user.setPassword("asdfa");
//        System.out.println(userService.insertUser(user));

        System.out.println(userService.updateStatus(149,1));

    }
}
