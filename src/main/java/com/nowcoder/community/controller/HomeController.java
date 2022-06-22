package com.nowcoder.community.controller;

import com.nowcoder.community.entity.DiscussPost;
import com.nowcoder.community.entity.Page;
import com.nowcoder.community.entity.User;
import com.nowcoder.community.service.DiscussPostService;
import com.nowcoder.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zmx
 * @create 2022-06-17 14:46
 */
@Controller
public class HomeController {
    @Autowired
    private DiscussPostService discussPostService;

    @Autowired
    private UserService userService;

    /**
     * 开发社区首页，显示前10个帖子
     * @param model
     * @return
     */
    @GetMapping("/index")
    public String getIndexPage(Model model, Page page){

        // 方法调用前，SpringMvc会自动实例化Model和Page,并将Page注入到Model中
        // 所以，在thymeleaf中可以直接访问Page对象中的数据
        page.setRows(discussPostService.findDiscussPostRows(0));

        page.setPath("/index");

        List<DiscussPost> list = discussPostService.findDiscussPost(0, page.getOffSet(), page.getLimit());

        List<Map<String,Object>> discussPosts = new ArrayList<>();

        //将帖子封装到model中，发送到前端
        if(list != null){
            for (DiscussPost post : list) {
                Map<String,Object> map = new HashMap<>();
                map.put("post",post);
                User user = userService.findUserById(post.getUserId());
                map.put("user",user);
                discussPosts.add(map);
            }
        }
        model.addAttribute("discussPosts",discussPosts);


        return "index";
    }



}
