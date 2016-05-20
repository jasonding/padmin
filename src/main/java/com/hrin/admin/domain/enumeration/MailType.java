package com.hrin.admin.domain.enumeration;

/**
 * Created by jason on 14-11-30.
 * 邮件类型：
 * 用户注册激活邮件
 * 找回密码邮件
 * 系统邮件
 */
public enum MailType {
    CONSUMER_ACTIVATE(1),
    CONSUMER_RETRIEVE(2),
    COMPANY_ACTIVATE(3),
    COMPANY_RETRIEVE(4);

    private int id;

    MailType(int id) {
        this.id = id;
    }
}
