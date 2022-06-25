package com.nowcoder.community.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.DigestUtils;
import java.util.UUID;

public class CommunityUtil {

    /**
     * 生成随机字符串
     * @return
     */
    public static String generateUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

    /**
     * MD5加密：为了用户密码加密
     * hello + 3e4a8 : 通过在用户密码基础上添加随机字符串，可以使得用户密码安全性上升
     * @param key 处理过后的密码
     * @return
     */
    public static String md5(String key){
        //如果字符串为空，就直接返回空
        if(StringUtils.isBlank(key)){
            return null;
        }
        //返回加密后的字符串
        return DigestUtils.md5DigestAsHex(key.getBytes());
    }


}
