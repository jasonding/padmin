package com.hrin.admin.task.callable.impl;

import com.hrin.admin.domain.HrinMail;
import com.hrin.admin.service.mail.MailService;
import com.hrin.admin.task.callable.AbstractHrinTask;

/**
 * Created by jason on 14-12-13.
 */
public class MailSenderTask extends AbstractHrinTask<Boolean>{
    private MailService mailService;
    private HrinMail mail;

    public MailSenderTask(MailService mailService, HrinMail mail) {
        this.mailService = mailService;
        this.mail = mail;
    }

    @Override
    protected Boolean doTask() throws Exception {
        boolean success = mailService.sendMail(mail);
        if(!success) {
            logger.error("发送邮件失败，请查看mail={}",mail);
        }
        return success;
    }
}
