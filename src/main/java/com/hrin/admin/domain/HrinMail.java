package com.hrin.admin.domain;

import com.hrin.admin.domain.enumeration.MailType;

/**
 * Created by jason on 14-12-13.
 */
public class HrinMail {

    private String from;//发送邮件
    private String to;//接受邮件
    private String subject;//主题
    private String content;//发送内容
    private boolean htmlStyle;//是否是html格式
    private MailType mailType;//邮件类型：用户激活，找回密码
    private String encode;//发送邮件内容的编码

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isHtmlStyle() {
        return htmlStyle;
    }

    public void setHtmlStyle(boolean htmlStyle) {
        this.htmlStyle = htmlStyle;
    }

    public MailType getMailType() {
        return mailType;
    }

    public void setMailType(MailType mailType) {
        this.mailType = mailType;
    }

    public String getEncode() {
        return encode;
    }

    public void setEncode(String encode) {
        this.encode = encode;
    }
}
