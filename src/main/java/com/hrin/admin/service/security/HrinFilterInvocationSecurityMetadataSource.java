package com.hrin.admin.service.security;

import com.hrin.admin.domain.admin.Privilege;
import com.hrin.admin.domain.admin.Resource;
import com.hrin.admin.service.manage.ResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by jason on 14-11-2.
 */
public class HrinFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource{
    private Logger logger = LoggerFactory.getLogger(HrinFilterInvocationSecurityMetadataSource.class);

    private ResourceService resourceService;

    @Autowired
    public void setResourceService(ResourceService resourceService) {
        this.resourceService = resourceService;
    }


    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        final HttpServletRequest request = ((FilterInvocation) object).getRequest();
//        for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : getAllConfigAttributes()) {
//            if (entry.getKey().matches(request)) {
//                return entry.getValue();
//            }
//        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        List<Resource> resourceList = resourceService.findAllWithPrivilege();
        Collection<ConfigAttribute> configAttributes = new HashSet<ConfigAttribute>();
        for (Resource resource : resourceList) {
            RequestMatcher requestMatcher = new RegexRequestMatcher(resource.getUrlPartten(),resource.getHttpMethod() == null? null:resource.getHttpMethod().name());
            Set<Privilege> privilegeSet = resource.getPrivilegeSet();
            for (Privilege privilege : privilegeSet) {
                configAttributes.add(new SecurityConfig(privilege.getConfigAttribute()));
            }
        }

        return configAttributes;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}
