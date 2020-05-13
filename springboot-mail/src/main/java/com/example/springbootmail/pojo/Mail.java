package com.example.springbootmail.pojo;

import lombok.Data;

@Data
public class Mail {

    //目标邮箱
    private String to;
    // 邮件标题
    private String title;
    // 邮件正文
    private String content;

    private String msgId;// 消息id
}
