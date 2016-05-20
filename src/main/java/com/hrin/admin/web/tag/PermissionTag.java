package com.hrin.admin.web.tag;

import com.hrin.admin.constant.HrinConstant;
import com.hrin.admin.domain.admin.*;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by jason on 15-1-19.
 */
public class PermissionTag extends SimpleTagSupport {
    private String url;
    private String method;

    @Override
    public void doTag() throws JspException, IOException {

        if(url == null || url.isEmpty()) return;

        User user = (User) this.getJspContext().findAttribute(HrinConstant.WEB_HRIN_USER_KEY);
        if(user == null) return;


        Set<Resource> resourceSet = new HashSet<Resource>();
        for (Role role : user.getRoleSet()) {
            for (Privilege privilege : role.getPrivilegeSet()) {
                resourceSet.addAll(privilege.getResourceSet());
            }
        }

        for (Resource resource : resourceSet) {
            if(url.equals(resource.getUrlPartten()) && method.equalsIgnoreCase(resource.getHttpMethod().name())) {
                this.getJspBody().invoke(null);
                return;
            }
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
