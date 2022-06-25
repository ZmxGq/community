package com.nowcoder.community.controller;

import com.nowcoder.community.entity.User;
import com.nowcoder.community.service.UserService;
import com.nowcoder.community.util.CommunityConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

/**
 * @author zmx
 * @create 2022-06-25 14:27
 */
@Controller
public class LoginController implements CommunityConstant {

    @Autowired
    private UserService userService;

   @GetMapping("/register")
    public String getRegisterPage(){
        return "site/register";
    }

   @GetMapping("/login")
    public String getLoginPage(){
        return "site/login";
    }

    /**
     * 调用用户注册的方法
     */
    @PostMapping("/register")
    public String register(Model model, User user){

        Map<String, Object> map = userService.register(user);
        if(map == null || map.isEmpty()){
            //注册成功
            model.addAttribute("msg","注册成功，我们已经向您的邮箱发送了一封激活邮件，请尽快激活！");
            //注册成功后要跳转到的页面
            model.addAttribute("target","/index");

            return "/site/operate-result";

        }else{
            //如果注册失败
            model.addAttribute("usernameMsg",map.get("usernameMsg"));
            model.addAttribute("passwordMsg",map.get("passwordMsg"));
            model.addAttribute("emailMsg",map.get("emailMsg"));
            return "site/register";

        }

    }

    //激活路径：https://localhost:8080/community/activation/101/code
    @GetMapping("/activation/{userId}/{code}")
    public String activation(Model model,
                             @PathVariable("userId") Integer userId,
                             @PathVariable("code") String code){

        int result = userService.activation(userId, code);
        if(result == ACTIVATION_SUCCESS){
            //激活成功
            model.addAttribute("msg","激活成功，您的账号已经可以正常使用了！");
            //激活成功后要跳转到的页面
            model.addAttribute("target","/login");

        }else if(result == ACTIVAION_REPEAT){

            //重复激活
            model.addAttribute("msg","无效操作，该账号已经激活过了！");
            //重复激活后要跳转到的页面
            model.addAttribute("target","/index");

        }else{

            //激活失败
            model.addAttribute("msg","激活失败，您提供的激活码不正确！");
            //激活失败后要跳转到的页面
            model.addAttribute("target","/index");

        }

        return "/site/operate-result";



    }




}
