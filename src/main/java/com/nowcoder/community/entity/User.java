package com.nowcoder.community.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author zmx
 * @create 2022-06-13 20:38
 */
@Data
public class User {

    private Integer id;
    private String username;
    private String password;
    private String salt;
    private String email;
    private Integer type;
    private Integer status;
    private String activationCode;
    //头像
    private String headerUrl;
    private Date createTime;
}
