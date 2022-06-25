package com.nowcoder.community.service;

import com.nowcoder.community.dao.UserMapper;
import com.nowcoder.community.entity.User;
import com.nowcoder.community.util.CommunityConstant;
import com.nowcoder.community.util.CommunityUtil;
import com.nowcoder.community.util.MailClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author zmx
 * @create 2022-06-13 20:35
 */
@Service
public class UserService implements CommunityConstant {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MailClient mailClient;

    @Autowired
    private TemplateEngine templateEngine;

    //注入域名
    @Value("${community.path.domain}")
    private String domain;
    //注入项目名
    @Value("${server.servlet.context-path}")
    private String contextPath;



    /**
     * 根据用户id查询用户信息
     * @param id
     * @return
     */
    public User findUserById(int id){
        return userMapper.findUserById(id);
    }

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    public User findUserByName(String username){
        return userMapper.findUserByName(username);
    }

    /**
     * 根据用户邮箱，查询用户信息
     * @param email
     * @return
     */
    public User findUserByEmail(String email){
        return userMapper.findUserByEmail(email);
    }

    /**
     * 插入用户
     */
    public int insertUser(User user){
        return userMapper.insertUser(user);
    }

    /**
     * 更改用户状态
     */
    public int updateStatus(Integer userId, Integer status){
        return userMapper.updateStatus(userId,status);
    }

    /**
     * 注册的方法
     * @param user
     * @return
     */
    public Map<String, Object> register(User user){
        Map<String,Object> map = new HashMap<>();

        //空值处理
        if(user == null){
            throw new IllegalArgumentException("参数不能为空!");
        }
        if(StringUtils.isBlank(user.getUsername())){
            map.put("usernameMsg","账号不能为空!");
            return map;
        }
        if(StringUtils.isBlank(user.getPassword())){
            map.put("passwordMsg","密码不能为空!");
            return map;
        }
        if(StringUtils.isBlank(user.getEmail())){
            map.put("emailMsg","邮箱不能为空!");
            return map;
        }

        //验证账号
        User userByName = userMapper.findUserByName(user.getUsername());
        if(userByName != null){
            map.put("usernameMsg","该账号已经存在!");
            return map;
        }

        //邮箱验证
        User userByEmail = userMapper.findUserByEmail(user.getEmail());
        if(userByEmail != null){
            map.put("emailMsg","该邮箱已经被注册!");
            return map;
        }

        //注册用户
        //设置随机字符串
        user.setSalt(CommunityUtil.generateUUID().substring(0,5));
        //对用户密码进行处理，使得安全性提高
        user.setPassword(CommunityUtil.md5(user.getPassword() + user.getSalt()));
        //设置用户的其他属性
        user.setType(0);//用户类型：注册的用户都是普通的用户
        user.setStatus(0);//用户是否激活：默认注册用户都还没有激活
        user.setActivationCode(CommunityUtil.generateUUID());//设置用户激活码
        //设置用户头像：%d是占位符，new Random().nextInt(1000)：生成0到1000的随机数字
        user.setHeaderUrl(String.format("http://images.nowcoder.com/head/%dt.png", new Random().nextInt(1000)));
        user.setCreateTime(new Date());//设置用户的创建时间


        //添加用户
        userMapper.insertUser(user);

        //激活邮件
        //设置邮件内容
        Context context = new Context();
        context.setVariable("email",user.getEmail());
        //设置激活路径：https://localhost:8080/community/activation/101/code
        //这里的用户Id是通过Mybatis自动填充进去的
        String url = domain + contextPath + "/activation/" + user.getId() + "/" + user.getActivationCode();
        context.setVariable("url",url);
        //设置整个邮件信息
        String content = templateEngine.process("/mail/activation", context);
        //发送邮件
        mailClient.sendMail(user.getEmail(), "激活账号", content);


        //此时，如果返回的map是空的，说明注册流程没有问题
        return map;
    }

    /**
     * 激活账号
     * @param userId 用户的Id
     * @param code 用户的激活码
     * @return
     */
    public int activation(int userId, String code){

        //在数据库中查询到该用户信息
        User user = userMapper.findUserById(userId);


        if(user.getStatus() == 1){
            //如果用户已经被激活
            return ACTIVAION_REPEAT;
        }else if(user.getStatus() == 0){
            //如果用户还未被激活
            userMapper.updateStatus(userId,1);
            return ACTIVATION_SUCCESS;
        }else{
            return ACTIVATION_FAILURE;
        }



    }





}
