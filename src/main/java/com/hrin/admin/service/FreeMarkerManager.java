package com.hrin.admin.service;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jason on 14-12-11.
 */
@Service("freeMarkerManager")
public class FreeMarkerManager {
    private Logger logger = LoggerFactory.getLogger(FreeMarkerManager.class);
    @Resource
    private FreeMarkerConfigurer freeMarkerConfigurer;

    public String processTemplateIntoString(String templateFileName,Map<String,Object> dataMap)  {
        Configuration configuration = freeMarkerConfigurer.getConfiguration();
        try {
            if(templateFileName == null || templateFileName.length()<=0) return null;
            Template template = configuration.getTemplate(templateFileName);
            return FreeMarkerTemplateUtils.processTemplateIntoString(template, dataMap);
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            return  null;
        }
    }
}
