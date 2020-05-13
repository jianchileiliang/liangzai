package com.example.springbootmail.service;

/**
 * @author
 * @Description:Service接口，定义邮件发送的方法
 * @date:
 */
public interface MailService {


    void sendSimpleMail(String to, String subject, String content);

    void sendHtmlMail(String to, String subject, String content);

    void sendAttachmentsMail(String to, String subject, String content, String filePath);

    void send(String to, String subject, String content);

}
