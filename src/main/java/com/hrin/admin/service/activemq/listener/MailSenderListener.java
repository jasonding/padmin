//package com.hrin.admin.service.activemq.listener;
//
//import com.hrin.admin.domain.HrinMail;
//import com.hrin.admin.service.FreeMarkerManager;
//import com.hrin.admin.service.mail.MailService;
//import com.hrin.admin.service.pool.ThreadPoolManager;
//import com.hrin.admin.task.callable.impl.MailSenderTask;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//import javax.jms.JMSException;
//import javax.jms.Message;
//import javax.jms.MessageListener;
//import javax.jms.ObjectMessage;
//import java.io.Serializable;
//
///**
// * Created by jason on 14-12-13.
// */
//@Service("mailSenderListener")
//public class MailSenderListener implements MessageListener {
//    private Logger logger = LoggerFactory.getLogger(MailSenderListener.class);
//    @Resource
//    private MailService mailService;
//    @Resource
//    private FreeMarkerManager freeMarkerManager;
//    private ThreadPoolManager threadPoolManager;
//    @Override
//    public void onMessage(Message message) {
//        if(message ==null || message instanceof ObjectMessage) return;
//        ObjectMessage objectMessage = (ObjectMessage) message;
//        Serializable data = null;
//        try {
//            data = objectMessage.getObject();
//            HrinMail mail = null;
//            MailSenderTask mailSenderTask = new MailSenderTask(mailService,mail);
//            threadPoolManager.getFixedThreadPool().submit(mailSenderTask);
//        } catch (JMSException e) {
//            logger.error("发送邮件监听器失败，从队列获取数据为：data＝{}",data);
//            logger.error(e.getMessage(),e);
//        }
//    }
//}
