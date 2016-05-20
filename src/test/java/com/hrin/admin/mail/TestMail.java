package com.hrin.admin.mail;

import com.hrin.admin.service.FreeMarkerManager;
import com.hrin.admin.service.mail.MailService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by jason on 14-12-10.
 */

public class TestMail {

    private ApplicationContext context;

    @Before
    public void init() {
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
    }

    @Test
    public void testMailSend() {
       
    }
}
