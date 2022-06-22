package com.nowcoder.community.entity;

/**
 * @author zmx
 * @create 2022-06-17 15:26
 */

import lombok.Data;

/**
 * 封装分页相关的信息
 */
@Data
public class Page {
    //当前的页码
    private int current = 1;
    //显示上限
    private int limit = 10;
    //数据总数（用于计算总的页数）
    private int rows;
    //查询路径(用来复用分页的链接)
    private String path;

    public void setCurrent(int current){
        if(current >= 1){
            this.current = current;
        }
    }

    public void setLimit(int limit){
        if(limit >= 1 && limit <= 100){
            this.limit = limit;
        }
    }

    public void setRows(int rows){
        if(rows >= 0){
            this.rows = rows;
        }
    }

    /**
     * 获取当前页面的起始行
     * @return
     */
    public int getOffSet(){
        // current * limit - limit
        return (current - 1) * limit;
    }

    /**
     * 获取总的页数
     */
    public int getTotal(){
        //rows / limit
        if(rows % limit == 0){
            return rows / limit;
        }else{
            return rows / limit  + 1;
        }
    }

    /**
     * 获取起始页码
     * @return
     */
    public int getFrom(){
        int from = current - 2;
        //判断起始页是否从1开始
        return from < 1 ? 1 : from;

    }

    /**
     * 获取结束页码
     * @return
     */
    public int getTo(){

        int to = current + 2;
        //判断最终页是否从最后一页开始
        return to > getTotal()? getTotal():to;

    }
}
