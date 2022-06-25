package com.nowcoder.community.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component //通过注解，将这个类交给spring管理
public class MailClient {

    //获取记录日志的对象
    private static final Logger logger = LoggerFactory.getLogger(MailClient.class);

    //Spring Email核心组件，负责发送邮件
    @Autowired
    private JavaMailSender mailSender;

    //发件人姓名
    @Value("${spring.mail.username}")
    private String from;

    /**
     *
     * @param to 收件人是谁
     * @param subject 邮件主题是啥
     * @param content 邮件内容是啥
     */
    public void sendMail(String to, String subject, String content){


        try {
            //用于封装邮件相关信息
            MimeMessage message = mailSender.createMimeMessage();
            //MimeMessageHelper 用于辅助构建MimeMessage信息
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setFrom(from); //设置发件人是谁
            helper.setTo(to); //设置收件人
            helper.setSubject(subject); //设置邮件的主题
            helper.setText(content, true); //后面这个参数代表支持html文本内容
            //发送邮件
            mailSender.send(helper.getMimeMessage());
        } catch (MessagingException e) {
            //更好的定位错误
            logger.error("发送邮件失败：" + e.getMessage());
        }

    }
}
