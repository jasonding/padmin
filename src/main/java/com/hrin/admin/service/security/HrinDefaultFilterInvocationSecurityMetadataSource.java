package com.hrin.admin.service.security;

import com.hrin.admin.domain.admin.Privilege;
import com.hrin.admin.domain.admin.Resource;
import com.hrin.admin.service.manage.ResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.access.intercept.DefaultFilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * Created by jason on 14-11-2.
 */
@Repository("hrinDefaultFilterInvocationSecurityMetadataSource")
public class HrinDefaultFilterInvocationSecurityMetadataSource implements FactoryBean<DefaultFilterInvocationSecurityMetadataSource> {
    private Logger logger = LoggerFactory.getLogger(HrinDefaultFilterInvocationSecurityMetadataSource.class);

    private ResourceService resourceService;

    @Autowired
    public void setResourceService(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @Override
    public DefaultFilterInvocationSecurityMetadataSource getObject() throws Exception {
        logger.info("注册DefaultFilterInvocationSecurityMetadataSource开始");
        LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> requestMap = new LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>>();
        List<Resource> resourceList = resourceService.findAllWithPrivilege();
        for (Resource resource : resourceList) {
            RequestMatcher requestMatcher = new RegexRequestMatcher(resource.getUrlPartten(),resource.getHttpMethod() == null? null:resource.getHttpMethod().name());
            Collection<ConfigAttribute> configAttributes = new HashSet<ConfigAttribute>();
            Set<Privilege> privilegeSet = resource.getPrivilegeSet();
            for (Privilege privilege : privilegeSet) {
                configAttributes.add(new SecurityConfig(privilege.getConfigAttribute()));
            }
            requestMap.put(requestMatcher,configAttributes);
        }
        logger.info("初始化系统权限:{}",requestMap);
        return new DefaultFilterInvocationSecurityMetadataSource(requestMap);
    }

    @Override
    public Class<?> getObjectType() {
        return DefaultFilterInvocationSecurityMetadataSource.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
