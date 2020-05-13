package com.example.springbootmail;

import com.example.springbootmail.pojo.Mail;
import com.example.springbootmail.service.impl.TestServiceImpl;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.example.springbootmail.service.MailService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {
    @Autowired
    private MailService mailService;

    @Autowired
    private TemplateEngine templateEngine;
    @Autowired
    private TestServiceImpl testServiceImpl;


    @Test
    public void send() {
        Mail mail  = new Mail();
        mail.setTo("1504848241@qq.com");
        mail.setContent("邮件");
        mail.setTitle("测试发送邮件");
        testServiceImpl.send(mail);
    }



    @Test
    public void testSimpleMail() throws Exception {
        mailService.sendSimpleMail("xxxx@qq.com", "test simple mail", " hello this is simple mail");
    }
    /**
     * 发送简单邮件时发生异常！
     * org.springframework.mail.MailAuthenticationException: Authentication failed;
     * nested exception is javax.mail.AuthenticationFailedException: 535 Error: authentication failed
     *
     * application.properties文件中的password是授权码，而不是真的登录密码
     *
     *  com.sun.mail.util.MailConnectException: Couldn't connect to host, port: smtp.163.com , 25; timeout -1;
     *  连不上
     *  spring.mail.host=smtp.163.com 后面多了一个空格
     */

    /**
     * @Description:发送模板邮件
     * @author:
     * @date:
     */
    @Test
    public void sendTemplateMail() {
        //创建邮件正文
        Context context = new Context();
        context.setVariable("user", "毛毛");
        context.setVariable("web", "梁仔网络");
        context.setVariable("company", "梁仔网络");
        context.setVariable("product", "梁仔");
        String emailContent = templateEngine.process("emailTemplate", context);
        mailService.sendHtmlMail("18801269400@163.com", "主题：这是模板邮件", emailContent);
    }

    /**
     * @Description:发送带有附件的邮件
     * @author
     * @date:
     */
    @Test
    public void sendAttachmentsMail() {
        String filePath = "D:\\课外书籍\\jQuery权威指南.pdf";
        mailService.sendAttachmentsMail("1504848241@qq.com", "主题：带附件的邮件", "有附件，请查收！", filePath);
    }

}
