package com.nowcoder.community.entity;

import lombok.Data;

import java.util.Date;

/**
 * 用户类
 * @author zmx
 * @create 2022-06-13 20:38
 */
@Data
public class User {

    private Integer id;
    private String username;
    private String password;
    //随机字符串，用于密码加密
    private String salt;
    private String email;
    //用户的类型
    private Integer type;
    //用户的状态：是否激活
    private Integer status;
    //账户的激活码
    private String activationCode;
    //头像
    private String headerUrl;
    private Date createTime;
}
