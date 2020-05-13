package com.example.springbootmail.controller;




import com.example.springbootmail.common.ServerResponse;
import com.example.springbootmail.pojo.Mail;
import com.example.springbootmail.service.TestService;
import com.example.springbootmail.util.MailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.example.springbootmail.service.MailService;

import javax.annotation.Resource;

/**
 * @Description:发送邮件的Controller
 * @author
 * @date:
 */
@RequestMapping("mail")
@RestController
public class MailController {

    @Autowired
    private MailService mailService;

    @Autowired
    private TestService testService;

    @Autowired
    private TemplateEngine templateEngine;

    @Resource
    private MailUtil mailUtil;


    @PostMapping("/send")
    public ServerResponse send(@RequestBody Mail mail) {

        return testService.send(mail);
    }
    /**
     * @Description:发送简单邮件(收件人，主题，内容都暂时写死)
     * 访问地址：http://localhost:8080/mail/sendSimpleMail
     * @return
     * @author
     */
    @RequestMapping("/sendSimpleMail")
    public String sendSimpleMail() {
        String to = "xxxx@qq.com";
        String subject = "test simple mail";
        String content = "hello, this is simple mail";
        mailService.sendSimpleMail(to, subject, content);
        return "success";
    }
    /**
     * @Description:发送Html格式的邮件(收件人，主题，内容都暂时写死)
     * 访问地址：http://localhost:8080/mail/sendHtmlMail
     * @return
     * @author:
     * @date:
     */
    @RequestMapping("/sendHtmlMail")
    public String  sendHtmlMail() {
        String to = "xxxx@qq.com";
        String subject = "test html mail";
        String content = "hello, this is html mail";
        mailService.sendHtmlMail(to, subject, content);
        return "success";
    }



    /**
     * @Description:发送带有附件的邮件
     * 访问地址：http://localhost:8080/mail/sendAttachmentsMail
     * @author:zoey
     * @date:2018年3月16日
     */
    @RequestMapping("/sendAttachmentsMail")
    public String sendAttachmentsMail() {
        String filePath="D:\\课外书籍\\jQuery权威指南.pdf";
        mailService.sendAttachmentsMail("xxxx@qq.com", "主题：带附件的邮件", "有附件，请查收！", filePath);
        return "success";
    }


    /**
     * @Description:发送模板邮件
     * 访问地址：http://localhost:8080/mail/sendTemplateMail
     * @author:zoey
     * @date:2018年3月16日
     */
    @RequestMapping("/sendTemplateMail")
    public String sendTemplateMail() {
        //创建邮件正文
        Context context = new Context();
        context.setVariable("user", "zoey");
        context.setVariable("web", "夏梦雪");
        context.setVariable("company", "测试公司");
        context.setVariable("product", "梦想产品");
        String emailContent = templateEngine.process("emailTemplate", context);
        mailService.sendHtmlMail("xxxx@qq.com", "主题：这是模板邮件", emailContent);
        return "sucess";
    }

}
