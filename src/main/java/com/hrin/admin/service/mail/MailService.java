package com.hrin.admin.service.mail;

import com.hrin.admin.domain.HrinMail;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.Date;

/**
 * Created by jason on 14-12-10.
 */
@Service("mailService")
public class MailService {
    private JavaMailSender mailSender;

    public boolean sendMail(HrinMail mail) {
        if(mail == null) return false;
        boolean success = true;
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            /**
             * new MimeMessageHelper(mimeMessage,true)之true个人见解：
             * 关于true参数,官方文档是这样解释的：
             * use the true flag to indicate you need a multipart message
             * 翻译过来就是：使用true,以表明你需要多个消息
             * 再去翻一下MimeMessageHelper的API,找到这样一句话：
             * supporting alternative texts, inline elements and attachments
             * 也就是说,如果要支持内联元素(HTML)和附件就必须给定第二个参数为true
             * 否则抛出 java.lang.IllegalStateException 异常
             */
            /**
             * 刚开始收到邮件显示中文乱码,于是赶紧查看官方文档,没有提到,再查阅API,也没有查到Set编码的方法,
             * 于是就在HTML里面自己指定编码,也不起作用,邮件还是显示中文乱码;
             * 最后的结论是：如果HTML内容含有中文必须指定HTML的编码,默认是ISO-8859-1,所以会显示中文乱码
             * 在MimeMessageHelper的构造方法中找到：
             * MimeMessageHelper(MimeMessage mimeMessage, boolean multipart, String encoding)
             * 实验一下,OK,原来编码是在这儿设置,没事了,中文正常显示
             */
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, mail.getEncode());
            messageHelper.setFrom(mail.getFrom()); //设置发件人Email
            messageHelper.setTo(mail.getTo());          //设定收件人Email
            messageHelper.setSubject(mail.getSubject()); //设置邮件主题
            if(mail.isHtmlStyle()) {
                messageHelper.setText(mail.getContent(), true);
            }else {
                messageHelper.setText(mail.getContent());
            }
            //步骤 1
            //资源的引用方法：cid:你自己设置的资源ID
            /**
             * ClassPathResource：很明显就是类路径资源,我这里的附件是在项目里的,所以需要用ClassPathResource
             * 如果是系统文件资源就不能用ClassPathResource,而要用FileSystemResource,例：
             * FileSystemResource file = new FileSystemResource(new File("D:/woniu.png"));
             */
            /**
             * 如果想在HTML中使用资源,必须在HTML中通过资源 ID 先引用资源,然后才来加载资源
             */
            //步骤 2
            //ClassPathResource image = new ClassPathResource("images/body.png");
            //messageHelper.addInline("imageid", image);
            mailSender.send(mimeMessage);    //发送HTML邮件

        } catch (Exception e) {
            System.out.println("异常信息：" + e);
            success = false;
        }
        return success;

    }

    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

}
