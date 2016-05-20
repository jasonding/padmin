package com.hrin.admin.web.filter;

import com.hrin.admin.constant.HrinConstant;
import com.hrin.admin.domain.admin.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
* Created by jason on 14-12-25.
*/
public class LogFilter implements Filter {
    private Logger logger = LoggerFactory.getLogger(LogFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        HttpSession session = httpServletRequest.getSession();
        User user = (User) session.getAttribute(HrinConstant.WEB_HRIN_USER_KEY);
        StringBuilder log = new StringBuilder();
        if(user != null) {
            log.append("访问用户:").append(user.getUsername()).append("  角色:").append(user.getRoleSet());
        }
        log.append(" 访问地址:").append(httpServletRequest.getRequestURI());
        logger.info(log.toString());
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }

}
