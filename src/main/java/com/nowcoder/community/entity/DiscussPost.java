package com.nowcoder.community.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author zmx
 * @create 2022-06-12 20:13
 */
@Data
public class DiscussPost {
    private Integer id;
    private Integer userId;
    private String title;
    private String content;
    private Integer type;
    private Integer status;
    private Date createTime;
    private int commentCount;
    private double score;
}
