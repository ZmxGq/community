package com.nowcoder.community;

import com.nowcoder.community.util.MailClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * @author zmx
 * @create 2022-06-25 11:52
 */
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class MailTests {

    @Autowired
    private MailClient mailClient;

    //在测试类中主动调用模板引擎，负责格式化模板
    @Autowired
    private TemplateEngine templateEngine;

    @Test
    public void testMail(){
        mailClient.sendMail("1245878944@qq.com","TEST","Hello,Mail!");
    }

    @Test
    public void testHtmlMail(){
        //调用thymeleaf模板引擎
        Context context = new Context();

        context.setVariable("username","sunday");

        //获取邮件的内容：HTML网页
        String content = templateEngine.process("/mail/demo", context);
        System.out.println(content);

        //发送邮件
        mailClient.sendMail("1245878944@qq.com","HTML",content);



    }

}
